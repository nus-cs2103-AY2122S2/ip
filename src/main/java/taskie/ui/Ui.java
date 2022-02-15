package taskie.ui;

import taskie.task.Task;

import java.util.List;

/**
 * A class that contains methods to print responses to the screen.
 */
public class Ui {
    private String lineBreak = "-------------------------------\n";
    private String welcomeMessage = "Hello! I'm Taskie, I take care of your tasks\nWhat can I do for you?\n";
    private String goodbyeMessage = "Bye. Hope to see you again soon!";

    /**
     * Returns Ui welcome message.
     *
     * @return Welcome message.
     */
    public String greet() {
        return welcomeMessage;
    }

    /**
     * Returns Ui goodbye message.
     *
     * @return Goodbye message.
     */
    public String goodbye() {
        return goodbyeMessage;
    }

    /**
     * Returns Ui Line divider.
     *
     * @return Line divider.
     */
    public String showLine() {
        return lineBreak;
    }

    /**
     * Returns the successful task added message.
     *
     * @param task Task that was added.
     * @param noOfTasks Number of tasks that are currently in the list of tasks.
     * @return Task added message.
     */
    public String taskAddedMessage(Task task, int noOfTasks) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.";
    }

    /**
     * Returns the contents of a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     * @return String message of the contents of list.
     */
    public String list(List<Task> tasks) {
        String response = String.format("You currently have %d task in your list:\n", tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            response += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return response;
    }

    /**
     * Returns message of a successfully marked task.
     *
     * @param task Task that was successfully marked.
     * @return Task marked message.
     */
    public String taskMarkedMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Returns message of a successfully unmarked task.
     *
     * @param task Task that was successfully unmarked.
     * @return Task unmarked message.
     */
    public String taskUnmarkedMessage(Task task) {
        return String.format("OK! I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Returns message of a successfully deleted task.
     *
     * @param task Task that was successfully deleted.
     * @param noOfTasks Number of Tasks that are currently in the list of tasks.
     * @return Task deleted message.
     */
    public String taskDeleteMessage(Task task, int noOfTasks) {
        return String.format("OK! I've deleted this task:\n" + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Returns message to the user to check the number of fields in their input.
     *
     * @return Not enough fields message.
     */
    public String notEnoughFieldsMessage() {
        return "Not enough fields, please check your inputs and try again.";
    }

    /**
     * Prints out message to the user to check the index given in their input.
     *
     * @return Invalid index message.
     */
    public String invalidIndex() {
        return "Index provided is not an integer. Please try again";
    }

    /**
     * Returns message to the user to check the format of the date in their input.
     *
     * @return Invalid date message.
     */
    public String invalidDate() {
        return "Invalid date: Please format date as yyyy-mm-dd";
    }

    /**
     * Returns the contents of a list of task in a String.
     *
     * @return String contents of a list of task.
     */
    public String listFindResults(List<Task> result) {
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < result.size(); i++) {
            response += String.format("%d. %s\n", i + 1, result.get(i).toString());
        }
        return response;
    }

    /**
     * Returns task not found message.
     *
     * @return Task not found message.
     */
    public String taskNotFound() {
        return "Sorry! There are no matching tasks found.";
    }

    public String getHelpMessage() {
        return "To find out more about commands, please type \"help {commandName}\"\n"
                + "commandNames include: event, todo, deadline, find, mark, unmark, list, delete";
    }

    public String getHelpMessage(String keyword) {
        return parseKeyword(keyword);
    }

    private String parseKeyword(String keyword) {
        String response = "";
        switch (keyword) {
        case "list":
            response = "The command \"list\" accepts no arguments.\n"
                    + "It will list out all the task in the current tasklist";
            break;
        case "mark":
            response = "The command \"mark\" accepts one integer argument.\n"
                    + "It will mark the task at the index specified.\n"
                    + "use the command \"list\" to find the index of a task.";
            break;
        case "unmark":
            response = "The command \"unmark\" accepts one integer argument.\n"
                    + "It will unmark the task at the index specified.\n"
                    + "use the command \"list\" to find the index of a task.";
            break;
        case "delete":
            response = "The command \"delete\" accepts one integer argument.\n"
                    + "It will delete the task at the index specified.\n"
                    + "use the command \"list\" to find the index of a task.";
            break;
        case "todo":
            response = "The command \"todo\" accepts one String argument.\n"
                    + "It will add a new todo task.\n"
                    + "e.g. todo buy bread";
            break;
        case "event":
            response = "The command \"event\" accepts one String argument.\n"
                    + "It will add a new event task.\n"
                    + "Example format: event christmas dinner /at 2022-12-25";
            break;
        case "deadline":
            response = "The command \"deadline\" accepts one String argument.\n"
                    + "It will add a new deadline task.\n"
                    + "Example format: deadline prepare christmas dinner /by 2022-12-25";
            break;
        case "find":
            response = "The command \"find\" accepts one String argument.\n"
                    + "It will return a list of tasks containing the given String.\n"
                    + "e.g. find bread";
            break;
        default:
            response = getHelpMessage();
            break;
        }
        return response;
    }
}

