package duke.functionality;

import java.util.Scanner;

import duke.exception.BlankCommandException;

/**
 * Represents the User Interface of the Duke project. A <code> Ui </code> object corresponds
 * to all user interface displays. Such as the messages.
 */
public class Ui {
    private static final String GREETING = "Hello! I'm TaskJamie\nWhat can i do for you?";
    private static final String ENDING = "Bye. Hope to see you again soon!";

    /**
     * Returns nothing, but prints out the greeting message of Duke TaskBot.
     */
    public void showGreeting() {
        System.out.println(GREETING);
    }

    /**
     * Returns nothing, but prints out the leaving message of Duke TaskBot.
     */
    public void showEnding() {
        System.out.println(ENDING);
    }

    /**
     * Returns nothing, but prints out the error messages caught by Duke TaskBot.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Returns nothing, but prints out the error messages when loading in the text file.
     */
    public void showLoadingError(String error) {
        System.out.println(error);
    }

    /**
     * Returns a full command gotten from user input.
     * @return full command.
     * @throws BlankCommandException if user inputs nothing. Eg, " ".
     */
    public String readCommand() throws BlankCommandException {
        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        if (input.length() == 0) {
            throw new BlankCommandException();
        }
        return input;
    }
}
