package component;

import tasks.DeadLines;
import tasks.Events;
import tasks.Tasks;
import tasks.ToDos;


/**
 * A class that belongs to the component package.
 * This class encapsulates the possible user commands from Nexus.
 */
public class Command {
    private final String userInput;
    /**
     * {@link component.TaskList}
     */
    private final TaskList tasks;

    /**
     * Constructs Command class.
     * @param userInput User input as a type String.
     * @param t Provides access to TaskList for some manipulation in the Nexus program.
     */
    public Command(String userInput, TaskList t) {
        this.userInput = userInput;
        this.tasks = t;
    }

    /**
     * Runs command to return Nexus reply.
     * @return A string representing what Nexus replied.
     */
    public String runCommand() {
        String[] wordSplit = userInput.split(" ");
        String action = wordSplit[0];
        String[] split = userInput.split("/");
        int start = userInput.indexOf(" ") + 1;
        int end = userInput.lastIndexOf('/');
        String task = end == -1 ? "" : userInput.substring(start, end - 1);
        String details = split.length > 1 ? split[1].substring(3) : "";

        switch (action) {
        case "help":
            // Shows the commands that are available in Nexus.
            return printCommandsAvailable();
        case "list":
            return printList();
        case "mark":
            return mark(Integer.parseInt(wordSplit[1]) - 1);
        case "unmark":
            return unmark(Integer.parseInt(wordSplit[1]) - 1);
        case "todo":
            return addTask(new ToDos(userInput.substring(start), false));
        case "deadline":
            return addTask(new DeadLines(task, false, details));
        case "event":
            return addTask(new Events(task, false, details));
        case "delete":
            return deleteTask(Integer.parseInt(wordSplit[1]) - 1);
        case "find":
            return find(userInput.substring(5));
        default:
            return sayBye();
        }
    }

    /**
     * Prints commands that are available n Nexus.
     * @return A string representing what Nexus replied.
     */
    private String printCommandsAvailable() {
        String openingStatement = "Here are some of the available commands in Nexus: \n";
        String commands = "1. list\n2. mark"
                + "\n3. unmark\n4. todo\n5.deadline\n6. event\n"
                + "7. delete\n8. find\n9. bye";
        return openingStatement + commands;
    }

    /**
     * Finds Task that matches that description from the TaskList.
     * @param description Description to be used for matching.
     * @return A string representing what Nexus replied.
     */
    private String find(String description) {
        String nexusReply = "Here are the matching task in your list: ";
        String resultOfFind = tasks.findDescription(description);
        return nexusReply + "\n" + resultOfFind;
    }


    /**
     * Deletes the task in the TaskList.
     * @param index 0-based index for deletion of Task in the TaskList.
     * @return A string representing what Nexus replied.
     */
    private String deleteTask(int index) {
        String nexusReply = "Noted. I've removed this task:" + "\n" + tasks.remove(index);
        String additionalStatement = String.format("Now you have %d tasks in the list.", tasks.listSize());
        return nexusReply + "\n" + additionalStatement;
    }

    /**
     * Marks the task in the TaskList.
     * @param index 0-based index for users to mark the Task in the TaskList.
     * @return A string representing what Nexus replied.
     */
    private String mark(int index) {
        String nexusReply = "Nice! I've marked this task as done:";
        Tasks retrievedTask = tasks.getTask(index);
        retrievedTask.setMarked();
        return nexusReply + "\n" + retrievedTask;
    }

    /**
     * Un-marks the task in the TaskList.
     * @param index  0-based index for users to unmark the task in the TaskList.
     * @return A string representing what Nexus replied.
     */
    private String unmark(int index) {
        String nexusReply = "OK, I've marked this task as not done yet:";
        Tasks retrievedTask = tasks.getTask(index);
        retrievedTask.setUnmarked();
        return nexusReply + "\n" + retrievedTask;
    }

    /**
     * Adds task to the TaskList.
     * @param newTask Task to be added.
     * @return A string representing what Nexus replied.
     */
    private String addTask(Tasks newTask) {
        tasks.addTask(newTask);
        String nexusReply = "Got it. I've added this task:" + "\n" + " " + newTask;
        String amountOfTaskInList = String.format("Now you have %d tasks in the list.", tasks.listSize());
        return nexusReply + "\n" + amountOfTaskInList;
    }

    /**
     * Prints the TaskList in the Ui.
     * @return A string representing what Nexus replied.
     */
    private String printList() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.listSize(); i++) {
            String num = String.format("%d. ", i + 1);
            String res = num + tasks.getTask(i).toString();
            result.append("\n").append(res);
        }
        return result.toString();
    }

    /**
     * Prints the bye Ui for the user.
     * @return A string representing what Nexus replied.
     */
    private String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

}
