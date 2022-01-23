package spike.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Encapsulates the spike.ui.Ui of the bot.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
        greet();
    }

    /**
     * Gets command from the user.
     *
     * @return
     */
    public String getCommand() {
        // Get the command as one line
        String inputLine = in.nextLine();
        while (isEmptyCommand(inputLine)) {
            inputLine = in.nextLine();
        }
        return inputLine;
    }


    /**
     * Prints start up message
     *
     * @return
     */
    private void greet() {
        printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?");
    }

    /**
     * Checks whether the command enter is empty.
     */
    private boolean isEmptyCommand(String inputLine) {
        return inputLine.trim().isEmpty();
    }

    /**
     * Shows error in loading task file.
     */
    public void showLoadingError() {
        out.println("Sorry, I couldn't create the task list file for you.");
    }

    /**
     * Formats and prints general response.
     */
    public void printMsg(String msg) {
        out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }
}
