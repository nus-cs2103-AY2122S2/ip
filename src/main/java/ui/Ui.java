package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import exception.DukeException;
import task.Task;
import task.TaskList;

public class Ui {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    /**
     * Default class constructor
     */
    public Ui() {

    }

    /**
     * Reads the user's input and performs functions
     * for input processing.
     *
     * @return Parse user's response
     * @throws DukeException If user's response is invalid/recognized
     */
    public String readCommand() throws DukeException {
        String response = "";
        try {
            System.out.println("Enter command here: ");
            response = reader.readLine().trim();
        } catch (IOException e) {
            throw new DukeException("Invalid user input. Please try again.");
        } catch (NullPointerException e) {
            throw new DukeException("CTRL + C maybe? How did you know of this hack? bye!");
        }

        return response;
    }

    /**
     * Displays the dotted line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message
     * and appends the list of commands.
     */
    public void showWelcome() {
        String welcome = "Hello! I'm duke.Duke. Here is a list of commands for your reference!\n\n";
        System.out.println(welcome);
        getCommands();
    }

    /**
     * Displays the list of commands.
     */
    public String getCommands() {
        StringBuilder welcome = new StringBuilder(
                "1. todo [task]\n");
        welcome.append("2. deadline [task] /by [date]\n");
        welcome.append("3. event [task] /at [location]\n");
        welcome.append("4. list\n");
        welcome.append("5. mark X (mark X task as done)\n");
        welcome.append("6. unmark X (mark X task as undone)\n");
        welcome.append("7. delete X (delete X task from the list)\n");
        welcome.append("8. bye - exit duke.Duke bot\n");
        return welcome.toString();
    }

    /**
     * Displays tasks stored in the TaskList object.
     *
     * @param tasks TaskList that stores user's tasks
     */
    public ArrayList<String> getListOfTasksMessage(TaskList tasks) {
        ArrayList<String> taskMessages = new ArrayList<>();
        if (tasks.size() == 0) {
            taskMessages.add("Start by adding these commands:\n");
            taskMessages.add(getCommands());
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String message = i + 1 + ". " + tasks.get(i).toString() + "\n";
                taskMessages.add(message);
            }
        }
        return taskMessages;
    }

    /**
     * Displays found tasks stored in the TaskList object.
     *
     * @param tasks TaskList that contains the found tasks
     * @return List of tasks into an Arraylist
     */
    public ArrayList<String> getFoundTasksMessage(TaskList tasks) {
        ArrayList<String> taskMessages = new ArrayList<>();
        if (tasks.size() == 0) {
            taskMessages.add("Nothing was found :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String message = i + 1 + ". " + tasks.get(i).toString() + "\n";
                taskMessages.add(message);
            }
        }
        return taskMessages;
    }

    /**
     * Displays bot's response to user's command.
     * This method is invoked after a command is executed successfully.
     *
     * @param res Bot's response
     */
    public String getResponseMessage(String res) {
        switch (res) {
        case "mark":
            return "Nice! I've marked this task as done:\n";
        case "unmark":
            return "OK, I've marked this task as not done yet:\n";
        case "delete":
            return "Noted. I've removed this task:\n";
        case "todo":
        case "deadline":
        case "event":
            return "Got it. I've added this task:\n";
        default:
            return "Sorry! I do not really have a response for you :(";
        }
    }

    /**
     * Returns the message when a command
     * is unknown to the bot.
     *
     * @return Unrecognized command message
     */
    public String showIncorrectMessage () {
        return "OOPS!!! I'm sorry, but I don't know what that means :(";
    }

    /**
     * Displays final message when the program is terminated.
     */
    public String getTerminatingMessage () {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays loading error message when cache file cannot be located.
     */
    public void showLoadingError() {
        System.out.println("Cache conversation not detected. Creating a new session with duke.Duke bot.\n");
    }

    /**
     * Displays the total amount of user's tasks.
     *
     * @param tasks TaskList that stores user's tasks
     * @return String response of the bot
     */
    public String getTasksCountMessage(TaskList tasks) {
        return "Now you have " + tasks.size() + " task(s) in the list.\n";
    }

    /**
     * Displays invalid TaskList index range message.
     */
    public String showInvalidRange() {
        return "Invalid number. Try again!";
    }

    /**
     * Displays task's details.
     *
     * @param task Specific user's task
     * @return String message of a task
     */
    public String getTaskMessage(Task task) {
        return task.toString();
    }

    /**
     * Displays error details.
     *
     * @param errorMessage Message to be displayed to the user.
     * @return Error message
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }
}
