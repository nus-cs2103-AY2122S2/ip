import java.util.Scanner;

/**
 * KoroBot is a chatbot with simple functionalities such as greet and echo.
 * @author Terng Yan Long
 * @version 1.0
 * @since 1.0
 */
public class Duke {
    private static final String BOT_NAME = "KoroBot";
    private static final String WELCOME_MESSAGE = "    ____________________________________________________________\n"
            + "    Hi! I'm " + BOT_NAME + "\n"
            + "    What can I do for you?\n"
            + "    ____________________________________________________________";
    private static final String EXIT_MESSAGE = "    ____________________________________________________________\n"
            + "    Bye! Hope to see you again soon :D\n"
            + "    ____________________________________________________________";

    /**
     * Greets the user by printing the default welcome message.
     */
    private static void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Exits the program when user inputs "bye".
     * Default exit message is printed as well.
     */
    private static void exit() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    /**
     * Echoes input message back to the user.
     *
     * @param userInput
     */
    private static void echo(String userInput) {
        String echoedMessage = "    ____________________________________________________________\n"
                + "    " + userInput + "\n"
                + "    ____________________________________________________________";
        System.out.println(echoedMessage);

    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = sc.next();
        }
        sc.close();
        exit();

    }
}

//TODO: Fix indentation, add author/comments/other formatting stuff
//TODO: OOP out welcome message
