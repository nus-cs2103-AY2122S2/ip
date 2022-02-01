package duke.ui;

import java.util.Scanner;

import duke.constants.Constants;

/**
 * Displays feedback to the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates Ui object to be used throughout the program's lifetime.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public void hello() {
        output(Constants.GREET);
    }

    /**
     * Displays any feedback to the user.
     * 
     * @param output output is provided by functions that want to display text to the user.
     */
    public void output(String output) {
        String result =
                "____________________________________________________________\n\n"
                + output
                + "\n____________________________________________________________\n\n";
        System.out.printf("%s", result);
    }

    /**
     * Display a message when there is an issue loading from the text file that holds tasks.
     */
    public void showLoadingError() {
        output(Constants.STORAGE_READ_MSG);
    }

    /**
     * Bids farewell to the user.
     */
    public void bye() {
        output(Constants.BYE);
    }

    /**
     * Takes in user input and cleans up.
     * 
     * @return Returns the trimmed (no whitespaces around) command for command processing.
     */
    public String readCommand() {
        String command = sc.nextLine().trim();

        return command;
    }

    /**
     * Shows the error feedback to the user.
     * 
     * @param errorMsg errorMsg is error feedback to be shown to user.
     */
    public void showGenericError(String errorMsg) {
        output(errorMsg);
    }
}
