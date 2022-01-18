import java.util.Scanner;

/**
 * KoroBot is a chatbot that tracks the list of tasks on hand.
 * @author Terng Yan Long
 * @version 4.0
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
     * Tests if the input string represents an integer value.
     *
     * @param input Target string.
     * @return true if the input string represents an integer value, and false otherwise.
     * @throws NumberFormatException thrown if the string does not represent an integer value.
     */
    private static boolean isInteger(String input) throws NumberFormatException  {
        boolean isInt = true;
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            isInt = false;
        }
        return isInt;
    }
    /**
     * Calls different functions according to the user's input command.
     * Any command that is not in the correct form will be treated as a normal addition to the list.
     *
     * @param userInput Command entered by the user.
     */
    private static void parse(String userInput) {
        String[] wordArray = userInput.trim().split(" ");
        if (wordArray[0].equals("list") & wordArray.length == 1) {
            listOfTasks.display();
        } else if (wordArray.length == 2) { // Checks if command contains 2 arguments
            if (wordArray[0].equals("mark") & isInteger(wordArray[1])) { // Check if command is of the form "mark int"
                int currTaskId = Integer.parseInt(wordArray[1]);
                if (currTaskId > 0 & currTaskId <= listOfTasks.getNumberOfTasks()) {
                    listOfTasks.mark(currTaskId); // Valid taskID, proceed to mark task
                } else {
                    listOfTasks.add(userInput);
                }
            } else if (wordArray[0].equals("unmark") & isInteger(wordArray[1])) { // Check if command is of the form
                                                                                  // "unmark int"
                int currTaskId = Integer.parseInt(wordArray[1]);
                if (currTaskId > 0 & currTaskId <= listOfTasks.getNumberOfTasks()) {
                    listOfTasks.unmark(currTaskId); // Valid taskID, proceed to unmark task
                } else {
                    listOfTasks.add(userInput);
                }
            } else {
                listOfTasks.add(userInput);
            }
        } else {
            listOfTasks.add(userInput);
        }
    }

    public static void main(String[] args) {
        greet();
        listOfTasks = new List(100); // Assume there will be no more than 100 tasks
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            parse(userInput);
            userInput = sc.nextLine();
        }
        sc.close();
        exit();
    }
}
