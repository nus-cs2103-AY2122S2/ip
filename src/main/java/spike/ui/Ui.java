package spike.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Encapsulates the Ui of the bot.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor using system input and output.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Initializes Ui for unit testing.
     *
     * @param inputStream
     * @param printStream
     */
    public Ui(InputStream inputStream, PrintStream printStream) {
        this.in = new Scanner(inputStream);
        this.out = printStream;
    }

    /**
     * Gets command from the user.
     *
     * @return string representation of the input
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
     */
    public void greet(int taskListSize) {
        if (taskListSize > 0) {
            printMsg("Welcome back! Enter 'list' command to see your task list.");
        } else {
            printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?");
        }
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
        printMsg("Sorry, I couldn't create the task list file for you.");
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
