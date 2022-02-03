package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String welcome = "Hello! I'm Duke. Here is a list of commands for your reference!\n\n";
        System.out.println(welcome);
        showCommands();
    }

    /**
     * Displays the list of commands.
     */
    public void showCommands() {
        StringBuilder welcome = new StringBuilder(
                "1. todo [task]\n");
        welcome.append("2. deadline [task] /by [date]\n");
        welcome.append("3. event [task] /at [location]\n");
        welcome.append("4. list\n");
        welcome.append("5. mark X (mark X task as done)\n");
        welcome.append("6. unmark X (mark X task as undone)\n");
        welcome.append("7. delete X (delete X task from the list)\n");
        welcome.append("8. bye - exit Duke bot\n");
        System.out.println(welcome.toString());
    }

    /**
     * Displays tasks stored in the TaskList object.
     *
     * @param tasks TaskList that stores user's tasks
     */
    public void showListOfTasksMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Start by adding these commands:\n");
            showCommands();
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays found tasks stored in the TaskList object.
     *
     * @param tasks TaskList that contains the found tasks
     */
    public void showFoundTasksMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Nothing was found :(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays bot's response to user's command.
     * This method is invoked after a command is executed successfully.
     *
     * @param res Bot's response
     */
    public void showResponseMessage(String res) {
        switch (res) {
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            break;
        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            break;
        case "delete":
            System.out.println("Noted. I've removed this java.task:");
            break;
        case "todo":
        case "deadline":
        case "event":
            System.out.println("Got it. I've added this task:");
            break;
        default:
            break;
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
    public void showTerminatingMessage () {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays loading error message when cache file cannot be located.
     */
    public void showLoadingError() {
        System.out.println("Cache conversation not detected. Creating a new session with Duke bot.");
    }

    /**
     * Displays the total amount of user's tasks.
     *
     * @param tasks TaskList that stores user's tasks
     */
    public void printTasksCountMessage(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays invalid TaskList index range message.
     */
    public void showInvalidRange() {
        System.out.println("Invalid number. Try again!");
    }

    /**
     * Displays task's details.
     *
     * @param task Specific user's task
     */
    public void showTaskMessage(Task task) {
        System.out.println(task.toString());
    }

    /**
     * Displays error details.
     *
     * @param errorMessage Message to be displayed to the user.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
