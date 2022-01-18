import java.util.Arrays;
import java.util.List;
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
    private static TaskList taskListOfTasks;

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
     * Removes the last character of a string
     *
     * @param str Target String
     * @return New string after removing the last character of the original string
     */
    public static String removeLastChar(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            return str.substring(0, str.length() - 1);
        }
    }

    /**
     * Calls different functions according to the user's input command.
     * Any command that is not in the correct form will be treated as a normal addition to the list.
     *
     * @param userInput Command entered by the user.
     */
    private static void parse(String userInput) {
        String[] wordArray = userInput.trim().split(" ");
        List<String> wordList = Arrays.asList(wordArray);
        if (wordList.get(0).equals("list") & wordList.size() == 1) {
            taskListOfTasks.display();
        } else if (wordList.get(0).equals("todo") & wordList.size() > 1) {
            taskListOfTasks.todo(userInput.substring(5));
        } else if (wordList.get(0).equals("deadline") & wordList.size() >= 4 & wordList.contains("/by")) {
            int separator = wordList.indexOf("/by");
            String desc = "";
            String dateTime = "";
            for (int i = 1; i < separator; i++) {
                desc += wordList.get(i);
                desc += " ";
            }
            for (int i = separator + 1; i < wordList.size(); i++) {
                dateTime += wordList.get(i);
                dateTime += " ";
            }
            taskListOfTasks.deadline(removeLastChar(desc), removeLastChar(dateTime));
        } else if (wordList.get(0).equals("event") & wordList.size() >= 4 & wordList.contains("/at")) {
            int separator = wordList.indexOf("/at");
            String desc = "";
            String dateTime = "";
            for (int i = 1; i < separator; i++) {
                desc += wordList.get(i);
                desc += " ";
            }
            for (int i = separator + 1; i < wordList.size(); i++) {
                dateTime += wordList.get(i);
                dateTime += " ";
            }
            taskListOfTasks.event(removeLastChar(desc), removeLastChar(dateTime));
        } else if (wordList.size() == 2) { // Checks if command contains 2 arguments
            if (wordList.get(0).equals("mark") & isInteger(wordList.get(1))) { // Check if command is of the
                                                                               // form "mark int"
                int currTaskId = Integer.parseInt(wordList.get(1));
                if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                    taskListOfTasks.mark(currTaskId); // Valid taskID, proceed to mark task
                } else {
                    taskListOfTasks.add(userInput);
                }
            } else if (wordList.get(0).equals("unmark") & isInteger(wordList.get(1))) { // Check if command is of the
                                                                                        // form "unmark int"
                int currTaskId = Integer.parseInt(wordArray[1]);
                if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                    taskListOfTasks.unmark(currTaskId); // Valid taskID, proceed to unmark task
                } else {
                    taskListOfTasks.add(userInput);
                }
            } else {
                taskListOfTasks.add(userInput);
            }
        } else {
            taskListOfTasks.add(userInput);
        }
    }

    public static void main(String[] args) {
        greet();
        taskListOfTasks = new TaskList(100); // Assume there will be no more than 100 tasks
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
