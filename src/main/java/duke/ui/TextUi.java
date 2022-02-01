package duke.ui;

import duke.command.CommandResult;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi object that handles terminal Input and Output of the program
 */
public class TextUi {
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
    private static final String MESSAGE_GOODBYE = "Goodbye!";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Default constructor for TextUi
     *
     * When instantiated, it prints the Logo
     */
    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
        printLogo();
    }

    /**
     * Gets the next line of user input
     * @return A String representation of one line of user input
     */
    public String getInput() {
        return in.nextLine();
    }

    /**
     * Prints the introduction message
     */
    public void printIntro() {
        printMessage(generateIntro());
    }

    /**
     * Prints the welcome message along with the Logo
     */
    public void printLogo() {
        printMessage("Hello from\n" + LOGO);
    }

    /**
     * Prints the goodbye message
     */
    public void printGoodbye() {
        printMessage(generateGoodbye());
    }

    /**
     * Prints the result from the given {@code CommandResult}
     * @param result The provided {@code CommandResult}
     */
    public void printResults(CommandResult result) {
        printMessage(generateResponse(result.toString()));
    }

    private void printMessage(String input) {
        out.println(input);
    }

    private static String generateResponse(String input) {
        String barrier = "<---------------------------------------------------------->\n";
        return barrier + input + "\n" + barrier;
    }

    private static String generateIntro() {
        return generateResponse(MESSAGE_WELCOME);
    }

    private static String generateGoodbye() {
        return generateResponse(MESSAGE_GOODBYE);
    }

}
