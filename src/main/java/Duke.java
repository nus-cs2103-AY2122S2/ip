import java.util.Scanner;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 2.0
 * @since 1.0
 */
public class Duke {
    private static final String BOT_NAME = "KoroBot";
    private static final String WELCOME_MESSAGE = "    ____________________________________________________________\n"
            + "     Hi! I'm " + BOT_NAME + "\n"
            + "     What can I do for you?\n"
            + "    ____________________________________________________________";
    private static final String EXIT_MESSAGE = "    ____________________________________________________________\n"
            + "     Bye! Hope to see you again soon :D\n"
            + "    ____________________________________________________________";
    private static List listOfTasks;

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
     * Calls different functions according to the user's input command
     *
     * @param userInput Command entered by the user.
     */
    private static void parse(String userInput) {
        if (userInput.equals("list")) {
            listOfTasks.display();
        } else {
            listOfTasks.add(userInput);
        }
    }

    public static void main(String[] args) {
        greet();
        listOfTasks = new List(100); // Assume there will be no more than 100 tasks
        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            parse(userInput);
            userInput = sc.next();
        }
        sc.close();
        exit();
    }
}
