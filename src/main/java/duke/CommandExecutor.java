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

        if (command.equals("bye")) {
            //end the program
            System.exit(0);
        } else if (command.equals("mark")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            return executeMarkTask(indexOfTask);
        } else if (command.equals("unmark")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            return executeUnmarkTask(indexOfTask);
        } else if (command.equals("todo")) {
            Task task = new Task(parsedInput.get(1));
            return executeAddTask(task);
        } else if (command.equals("event")) {
            Event task = new Event(parsedInput.get(1), parsedInput.get(2));
            return executeAddTask(task);
        } else if (command.equals("deadline")) {
            Deadline task = new Deadline(parsedInput.get(1), parsedInput.get(2));
            return executeAddTask(task);
        } else if (command.equals("delete")) {
            int indexOfTask = Integer.parseInt(parsedInput.get(1));
            return executeDeleteTask(indexOfTask);
        } else if (command.equals("list")) {
            return executeListTasks();
        } else if (command.equals("find")) {
            String keyword = parsedInput.get(1);
            return executeFind(keyword);
        }

        //should never be able to reach this return statement as if a bad input is given,
        //UnkownCommandException would already have been thrown earlier.
        assert "never reach here" == null;
        return "";
    }

    /**
     * Executes the marking of task as done.
     * @param indexOfTask index of the task to mark
     * @return string of output to print in gui for this action
     * @throws IOException if error encountered with writing to file
     */
    public String executeMarkTask(int indexOfTask) throws IOException{
        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this task as done:\n");
        str.append(taskManager.completeTask(indexOfTask));
        taskManager.saveTasks();
        return str.toString();
    }

    /**
     * Executes the unmarking of a task as done.
     * @param indexOfTask index of the task to unmark
     * @return string of output to print in gui for this action
     * @throws IOException if error encountered with writing to file
     */
    public String executeUnmarkTask(int indexOfTask) throws IOException{
        StringBuilder str = new StringBuilder();
        str.append("OK, I've marked this task as not done yet:\n");
        str.append(taskManager.removeCompletedStatusOfTask(indexOfTask));
        taskManager.saveTasks();
        return str.toString();
    }

    /**
     * Executes the adding of a task to Duke.
     * @param task task to add to Duke
     * @return string of output to print in gui for this action
     * @throws IOException if error encountered with writing to file
     */
    public String executeAddTask(Task task) throws IOException{
        taskManager.addTask(task);
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n");
        str.append("  " + task + "\n");
        str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks in the list.");
        taskManager.saveTasks();
        return str.toString();
    }

    /**
     * Executes the deletion of a task from Duke.
     * @param indexOfTask index of task to be deleted
     * @return string of output to print in gui for this action
     * @throws IOException
     */
    public String executeDeleteTask(int indexOfTask) throws IOException{
        Task task = taskManager.removeTask(indexOfTask);
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:\n");
        str.append("  " + task + "\n");
        str.append("Now you have " + taskManager.getNumberOfTasks() + " duke.tasks left in the list.");
        taskManager.saveTasks();
        return str.toString();
    }

    /**
     * Executes the listing of all tasks in Duke.
     * @return string of output to print to gui for this action
     */
    public String executeListTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Heres a list of your items:\n");
        str.append(taskManager.getPrintableListOfTasks());
        return str.toString();
    }

    /**
     * Executes the finding of a file in Duke using a keyword.
     * @param keyword keyword to look for in Duke
     * @return string of output to print to gui for this action
     */
    public String executeFind(String keyword) {
        ArrayList<Task> matchingTasks = taskManager.findListOfMatchingTasks(keyword);
        StringBuilder str = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            str.append("No matching tasks found.");
            return str.toString();
        }

        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            str.append((i + 1) + ". " + matchingTasks.get(i));
        }

        return str.toString();
    }


}
