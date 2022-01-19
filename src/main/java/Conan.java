/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Conan class.
 */

import java.util.Scanner;


public class Conan {

    // GREETING variable contains the introductory greetings.
    private final static String GREETING = "Hello There, My name is Conan! \n"
            + "Hope you're doing fine today! (^_^) \n"
            + "I'm a task manager, and I can help you keep up with your tasks.\n"
            + "Now before we start, lets get acquainted! Lets start with our names!";

    // SEPARATOR helps us distinguish between user commands and the task managers comments.
    private final static String SEPARATOR = "------------------------------------------------";

    // HELLO variable is used to store Hello greeting.
    private final static String HELLO = "Hello, ";

    // NTMY variable is followed by after the username.
    private final static String NTMY = "!, Nice to meet you! (*^_^*)";

    private final static String INITIAL_ASK = "So, tell me what would you like to do ";

    // ASK variable asks the user for tasks.
    private final static String ASK = "Please let me know if there's anything else you would like to add, ";

    // BYE variable stores bye, which recognises user exit command.
    private final static String BYE = "BYE";

    // GOODBYE variable stores a farewell message.
    private final static String GOODBYE = "Goodbye, ";

    // FAREWELL variable store the remaining goodbye message.
    private final static String FAREWELL = "\nHope I helped you complete your tasks!\n"
            + "Have a great day ahead, enjoy ! (^-^)/\n"
            + "Hope to see you next time! ";

    // Username is an instance variable that stores the name of the user.
    private final String username;


    /**
     * Constructor returns a new Conan object.
     * returns a new Conan instance.
     */
    Conan() {

        System.out.println(SEPARATOR);
        System.out.println(GREETING);

        System.out.println(SEPARATOR);
        Scanner sc = new Scanner(System.in);
        this.username = sc.nextLine();

        System.out.println(SEPARATOR);
        System.out.println(HELLO + this.username + NTMY);
        System.out.println(INITIAL_ASK + this.username);

        System.out.println(SEPARATOR);
    }

    /**
     * echo function echoes the message given by user.
     * @param message the message given by user.
     * @return CarryOn.NEXT to indicate the user wants to give more tasks.
     */
    private CarryOn echo(String message) {
        System.out.println(message);
        System.out.println(ASK + this.username);
        System.out.println(SEPARATOR);
        return CarryOn.NEXT;
    }

    /**
     * bye function bids user farewell and returns a trigger to end the program.
     * @return CarryOn.STOP to end the program.
     */
    private CarryOn bye() {
        System.out.println(GOODBYE + this.username + FAREWELL);
        System.out.println(SEPARATOR);
        return CarryOn.STOP;
    }


    /**
     * tell function gets the message from user and passes on to other
     *  function for a suitable response.
     * @param message the message given by user.
     * @return CarryOn.NEXT if the user wants to continue; else CarryOn.STOP.
     */
    CarryOn tell(String message) {
        System.out.println(SEPARATOR);
        // Checks if the user wants to exit.
        if (message.equalsIgnoreCase(BYE)) {
            return bye();
        }

        return echo(message);
    }
}
