package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates Duke's UI interactions between user and client.
 */
public class Ui {

    /**
     * Reads in user input
     *
     * @return User input string
     */
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    /**
     * Prints welcome message
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Duke\n What can I do for you?\n" +
                "Please enter a valid command in the following format:\n"
                + "1. list\n"
                + "2. todo {task}\n"
                + "3. deadline {task} /by {task deadline}\n"
                + "4. event {event name} /at {date}\n"
                + "5. delete {task index}\n"
                + "6. mark {task index}\n"
                + "7. unmark {task index}\n"
                + "8. find {keyword}";
    }

    /**
     * Prints farewell message
     */
    public String showFarewellMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints error message
     *
     * @param errorMessage error message
     */
    public String showErrorMessage(String errorMessage) {
        return "ERROR: " + errorMessage;
    }
}
