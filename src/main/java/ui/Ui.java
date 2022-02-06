package ui;

import duke.DukeException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for printing and scanning input from user.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads user input and returns the message.
     * @return String of the user input.
     */
    public String readUserInput() {
        return this.sc.nextLine();
    }

    /**
     * Gets error message from a exception.
     * @param d exception.
     * @return the message of d.
     */
    public String getErrorMessage(DukeException d) {
        return d.getMessage();
    }

    /**
     * Gets the string version of matching tasks.
     * @param matchTasks
     * @return the string version of matching tasks.
     */
    public String getMatchingTasksMessage(ArrayList<Task> matchTasks) {
        assert matchTasks != null;
        if (matchTasks.isEmpty()) {
            return "No task matches your keyword :(";
        } else {
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchTasks.size(); i++) {
                response.append(String.valueOf(i + 1));
                response.append(". ");
                response.append(matchTasks.get(i).toString());
                response.append("\n");
            }
            return response.toString();
        }
    }

    /**
     * Print messages from input.
     * @param message message that is supposed to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}