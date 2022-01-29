package duke.operations;

import duke.exceptions.EmptyInputException;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    private static final String SIGNATURE = " ____                         _____       _\n"
            + "| |_) |_   _ ___ ___ _   _  | (___   __ _| | ____ _\n"
            + "|  _ <| | | / __/ __| | | |  \\___ \\ / _` | |/ / _` |\\\n"
            + "| |_) | |_| \\__ \\__ \\ |_| |  ____) | (_| |   < (_| |\n"
            + "|____/ \\__,_|___/___/\\__, | |_____/ \\__,_|_|\\_\\__,_|\n"
            + "                      __/ |\n"
            + "                      |___/ \n";

    private static final String WELCOME_MESSAGE_ONE = "Tell me... have you seen a RED imposter among us?";
    private static final String WELCOME_MESSAGE_TWO = "If you have seen this SUSSY imposter,"
            + " please let me know immediately... otherwise how may I be of assistance?";
    private static final String GOODBYE_MESSAGE = "Better watch out for the imposter AMONG US!";

    /**
     * Prints a line.
     */
    public static void line() {
        printMessage(DIVIDER);
    }

    /**
     * Prints a String.
     *
     * @param str the string to be printed.
     */
    public static void printMessage(String str) {
        System.out.println(str);
    }

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        printMessage(DIVIDER);
        printMessage(WELCOME_MESSAGE_ONE);
        printMessage(WELCOME_MESSAGE_TWO);
        printMessage(DIVIDER);
    }

    /**
     * Prints a goodbye message.
     */
    public void showGoodbye() {
        printMessage(DIVIDER);
        printMessage(GOODBYE_MESSAGE);
        printMessage(SIGNATURE);
        printMessage(DIVIDER);
    }

    /**
     * Prints out the error.
     *
     * @param msg the string to be printed.
     */
    public void showError(String msg) {
        printMessage(msg);
    }

    /**
     * Prints out the duke error.
     *
     * @param msg the string to be printed.
     */
    public void showDukeError(String msg) {
        printMessage(msg);
    }

    /**
     * Reads the user input.
     *
     * @return returns the input keyed in by the user.
     * @throws EmptyInputException throws an exception when there is no input. E.g., "".
     */
    public String readCommand() throws EmptyInputException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        // Handle empty command
        if (input.equals("")) {
            throw new EmptyInputException();
        }
        return input;
    }
}
