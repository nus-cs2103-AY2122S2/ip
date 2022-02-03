package DukeComponent;

import Tasks.Tasks;
import Tasks.ToDos;
import Tasks.Events;
import Tasks.DeadLines;

/**
 * A class that belongs to the DukeComponent Package.
 * This class encapsulates the possible user commands from Duke.
 */
public class Command {
    private final String userInput;
    /**
     * {@link DukeComponent.TaskList}
     */
    private final TaskList tasks;

    /**
     * Constructor for Command class.
     * @param userInput User input as a type String.
     * @param t Provides access to TaskList for some manipulation in the Duke program.
     */
    public Command(String userInput, TaskList t) {
        this.userInput = userInput;
        this.tasks = t;
    }

    /**
     * Execution of command logic in Duke.
     */
    public String execute() {
        String[] wordSplit = userInput.split(" ");
        String action = wordSplit[0];
        String[] split = userInput.split("/");
        int start = userInput.indexOf(" ") + 1;
        int end = userInput.lastIndexOf('/');
        String task = end == -1 ? "" : userInput.substring(start, end - 1);
        String details = split.length > 1 ? split[1].substring(3) : "";

        switch (action) {
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
     * Finds Task that matches that description from the TaskList.
     * @param description Description to be used for matching.
     */
    private String find(String description) {
        String s = "Here are the matching task in your list: ";
        return s + "\n" + tasks.find(description);

    }


    /**
     * Deletes the task in the TaskList.
     * @param i 0-based index for deletion of Task in the TaskList.
     */
    private String deleteTask(int i) {
        String s = "Noted. I've removed this task:" + "\n" + tasks.remove(i);
        String res = String.format("Now you have %d tasks in the list.", tasks.size());
        return s + "\n" + res;
    }

    /**
     * Marks the task in the TaskList.
     * @param i 0-based index for users to mark the Task in the TaskList.
     */
    private String mark(int i) {
        String s = "Nice! I've marked this task as done:";
        Tasks t = tasks.get(i);
        t.setMarked(true);
        return s + "\n" + t;
    }

    /**
     * Un-marks the task in the TaskList.
     * @param i  0-based index for users to unmark the task in the TaskList.
     */
    private String unmark(int i) {
        String s = "OK, I've marked this task as not done yet:";
        Tasks t = tasks.get(i);
        t.setMarked(false);
        return s + "\n" + t;
    }

    /**
     * Adds task to the TaskList.
     * @param t Task to be added.
     */
    private String addTask(Tasks t) {
        tasks.add(t);
        String s = "Got it. I've added this task:" + "\n" +  " " + t;
        String res = String.format("Now you have %d tasks in the list.", tasks.size());
        return s + "\n" + res;
    }

    /**
     * Prints the TaskList in the Ui.
     */
    private String printList() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.format("%d. ", i + 1);
            String res = num + tasks.get(i).toString();
            result.append("\n").append(res);
        }
        return result.toString();
    }

    /**
     * Prints the bye Ui for the user.
     */
    private String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

}
