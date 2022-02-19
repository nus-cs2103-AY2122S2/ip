package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class that handles input and output with user.
 */
public class TextUI {

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for TextUI.
     *
     * @param in To receive user input by scanning.
     * @param out output to print to.
     */
    public TextUI(InputStream in, PrintStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Constructor for TextUI with default System in and out.
     */
    public TextUI() {
        this(System.in, System.out);
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Gets next line entered by user.
     * Ignores empty lines.
     *
     * @return raw user input
     */
    public String getUserCommand() {
        String currInput = in.nextLine().trim();
        while (shouldIgnore(currInput)) {
            currInput = in.nextLine().trim();
        }
        return currInput;
    }

    /**
     * Formats the message with borders and prints it.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        String textBorder = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        String textToPrint = "\n" + textBorder + message + "\n" + textBorder + "\n";
        out.print(textToPrint);
    }

    /**
     * Generates and print the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.print("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        out.println("\nGreetings from\n" + logo);
        out.println("How may I assist you?");
        out.print("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
    }

    /**
     * Generates the welcome message upon the start of the application.
     *
     * @return String of welcome message.
     */
    public String getWelcomeMessage() {
        return "Greetings from Duke. \nHow may I assist you today?";
    }
}
