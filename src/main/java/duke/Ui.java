package duke;

import java.util.Scanner;

/**
 * Deals with interactions with User and passes accordingly to relevant classes
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Ui {
    /**
     * A divider for design purposes
     */
    private static final String DIVIDER = "\n____________________________________________________________\n";

    private final Parser parser;

    /**
     * Constructor for Ui class. Initializes the Parser object.
     */
    public Ui() {
        parser = new Parser();
    }

    public void userInput(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String input = "";

        while (!input.equals("bye")) {
            input = sc.nextLine();

            try {
                String message = parser.processMessage(input, tasks, storage);

                if (message == null) {
                    break;
                } else {
                    showMessage(message);
                }
            } catch (DukeException e) {
                showLoadingError(e);
            }
        }
    }

    /**
     * Outputs a message to the user in a particular design (enclosed with dividers)
     * @param message The message string to be shown to the user
     */
    public void showMessage(String message) {
        System.out.println(DIVIDER + message + DIVIDER);
    }

    /**
     * Outputs an error message during runtime.
     * @param e The exception that was thrown
     */
    public void showLoadingError(Exception e) {
        showMessage(e.getMessage());
    }

    /**
     * Returns the message the user will be shown upon starting the program.
     */
    public void showWelcomeMessage() {
        showMessage("Why hello there! My name is Wensleydale.\nWhat do you need?");
    }

    /**
     * Retruns the message the user will be shown upon exiting the program
     */
    public void showFarewellMessage() {
        showMessage("Farewell then!");
    }
}
