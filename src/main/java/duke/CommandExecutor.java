package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.exceptions.RequiredInformationMissingException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.UnknownFileEntry;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

/**
 * Class that handles parsing and executing of inputs read from the GUI.
 */
public class CommandExecutor {
    private final TaskManager taskManager;

    /**
     * Creates instance of CommandExecutor.
     *
     * @throws IOException      if some error reading task file occurs.
     * @throws UnknownFileEntry if unknown line read in task file.
     */
    public CommandExecutor() throws IOException, UnknownFileEntry {
        this.taskManager = new TaskManager();
    }

    /**
     * Main method, exceutes a command passed from the GUI.
     *
     * @param input
     * @returns string representing dukes response.
     */
    public String executeCommand(String input) throws RequiredInformationMissingException, UnknownCommandException,
            IOException {
        ArrayList<String> parsedInput = Parser.parseInput(input);
        String command = parsedInput.get(0);
        StringBuilder str = new StringBuilder();
        if (command.equals("bye")) {
            //end the program
            System.exit(0);
        } else if (command.equals("mark")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            str.append("Nice! I've marked this task as done:\n");
            str.append(taskManager.completeTask(indexOfTask));
        } else if (command.equals("unmark")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            str.append("OK, I've marked this task as not done yet:\n");
            str.append(taskManager.removeCompletedStatusOfTask(indexOfTask));
        } else if (command.equals("todo")) {
            Task task = new Task(parsedInput.get(1));
            taskManager.addTask(task);
            str.append("Got it. I've added this task:\n");
            str.append("  " + task + "\n");
            str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
        } else if (command.equals("event")) {
            Event task = new Event(parsedInput.get(1), parsedInput.get(2));
            taskManager.addTask(task);
            str.append("Got it. I've added this task:\n");
            str.append("  " + task + "\n");
            str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
        } else if (command.equals("deadline")) {
            Deadline task = new Deadline(parsedInput.get(1), parsedInput.get(2));
            taskManager.addTask(task);
            str.append("Got it. I've added this task:\n");
            str.append("  " + task + "\n");
            str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
        } else if (command.equals("delete")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            Task task = taskManager.removeTask(indexOfTask);
            str.append("Noted. I've removed this task:\n");
            str.append("  " + task + "\n");
            str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks left in the list.");
        } else if (command.equals("list")) {
            str.append("Heres a list of your items:\n");
            str.append(taskManager.getPrintableListOfTasks());
        } else if (command.equals("find")) {
            ArrayList<Task> matchingTasks = taskManager.findListOfMatchingTasks(parsedInput.get(1));
            if (matchingTasks.isEmpty()) {
                str.append("No matching tasks found.");
            } else {
                str.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    str.append((i + 1) + ". " + matchingTasks.get(i));
                }
            }
        }

        //save the tasks to the tasks file at the end of each command
        taskManager.saveTasks();

        return str.toString();
    }
}
