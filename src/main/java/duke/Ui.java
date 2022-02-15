package duke;

import java.util.Scanner;

/**
 * The Ui class is responsible for dealing with all user interactions.
 */
public class Ui {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static final String LOGO  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INITIALIZATION_MESSAGE = "Hello from\n" + LOGO + "enter a command\n" +
                "use command 'help' to see list of commands";

    /**
     * Initialise the Parser that will be used to parse all user inputs.
     *
     * @param parser The Parser object.
     */
    public static void initialize(Parser parser) {

        System.out.println(ANSI_BLUE + INITIALIZATION_MESSAGE + ANSI_RESET);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (true) {
            String returnMessage = parser.parse(userInput);
            System.out.print(ANSI_BLUE + returnMessage + ANSI_RESET);
            if (parser.checkByeCommand()) {
                break;
            }
            userInput = sc.nextLine();
        }
       sc.close();
    }

    public static void print(String message) {
        System.out.print(ANSI_BLUE + message + ANSI_RESET);
    }
}
