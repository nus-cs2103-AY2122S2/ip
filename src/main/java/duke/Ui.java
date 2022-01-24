package duke;

import java.util.Scanner;

/**
 * Deals with interactions with User and passes accordingly to relevent classes
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Ui {
    /**
     * A divider for design purposes
     */
    public static final String DIVIDER = "\n____________________________________________________________\n";

    private final Parser parser;

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

    public void showMessage(String message) {
        System.out.println(DIVIDER + message + DIVIDER);
    }

    public void showLoadingError(Exception e) {
        showMessage(e.getMessage());
    }

    public void showWelcomeMessage() {
        showMessage("Why hello there! My name is Wensleydale.\nWhat do you need?");
    }

    public void showFarewellMessage() {
        showMessage("Farewell then!");
    }
}
