import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
     * Any command that is not in the correct form will be throw an "error" message.
     *
     * @param userInput Command entered by the user.
     */
    private static void parse(String userInput) throws DukeException {
        String[] wordArray = userInput.trim().split(" ");
        List<String> wordList = Arrays.asList(wordArray);
        String firstWord = wordList.get(0);

        switch (firstWord) {
            case ("list"):
                if (wordList.size() == 1) {
                    taskListOfTasks.display();
                } else {
                    throw new InvalidCommand("This command should not have any arguments :(");
                }
                break;
            case ("todo"):
                if (wordList.size() > 1) {
                    taskListOfTasks.todo(userInput.substring(5));
                } else {
                    throw new InvalidCommand("The description of a todo cannot be empty :(");
                }
                break;
            case ("deadline"):
                if (wordList.size() < 4) {
                    throw new InvalidCommand("Incorrect number of arguments supplied :(");
                } else if (!wordList.contains("/by")) {
                    throw new InvalidCommand("Please follow this format: deadline <task> " +
                            "/by <date in yyyy-MM-dd> <time in 24hrs format> ");
                } else {
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
                    dateTime = removeLastChar(dateTime);
                    String[] dateTimeArray = dateTime.split(" ");

                    if (dateTimeArray.length > 2 || dateTimeArray.length < 1) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    LocalDate newDate = convertDate(dateTimeArray[0]);
                    LocalTime newTime = null;
                    if (dateTimeArray.length > 1) { // Optional time input
                       newTime = convertTime(dateTimeArray[1]);
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newTime != null) {
                        if (newDate.isEqual(nowDate) & newTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        }
                    }
                    taskListOfTasks.deadline(removeLastChar(desc), newDate, newTime);
                }
                break;
            case ("event"):
                if (wordList.size() < 4) {
                    throw new InvalidCommand("Incorrect number of arguments supplied :(");
                } else if (!wordList.contains("/at")) {
                    throw new InvalidCommand("Please follow this format: event <task> " +
                            "/at <date in yyyy-MM-dd> <time in 24hrs format> ");
                } else {
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
                    dateTime = removeLastChar(dateTime);
                    String[] dateTimeArray = dateTime.split(" ");

                    if (dateTimeArray.length > 3 || dateTimeArray.length < 1) {
                        throw new InvalidCommand("Incorrect number of arguments supplied :(");
                    }

                    // Parse user input into LocalDate/LocalTime if it is in the correct format.
                    LocalDate newDate = convertDate(dateTimeArray[0]);
                    LocalTime newStartTime = null;
                    LocalTime newEndTime = null;
                    if (dateTimeArray.length > 1) { // Optional start time input
                        newStartTime = convertTime(dateTimeArray[1]);
                    }
                    if (dateTimeArray.length > 2) { // Optional end time input
                        newEndTime = convertTime(dateTimeArray[2]);
                    }

                    // Check if date/time specified is in the present.
                    Clock cl = Clock.systemUTC();
                    LocalDate nowDate = LocalDate.now(cl);
                    LocalTime nowTime = LocalTime.now(cl);
                    if (newDate.isBefore(nowDate)) {
                        throw new InvalidDateTime("You cannot travel back in time!");
                    }
                    if (newStartTime != null) {
                        if (newDate.isEqual(nowDate) & newStartTime.isBefore(nowTime)) {
                            throw new InvalidDateTime("You cannot travel back in time!");
                        } else if (newEndTime != null) {
                            if (newDate.isEqual(nowDate) & newEndTime.isBefore(nowTime)) {
                                throw new InvalidDateTime("You cannot travel back in time!");
                            } else if (!newEndTime.isAfter(newStartTime)) {
                                throw new InvalidDateTime("The end time must be after the start time");
                            }
                        }
                    }
                    taskListOfTasks.event(removeLastChar(desc), newDate, newStartTime, newEndTime);
                }
                break;
            case("mark"):
                if (wordList.size() != 2) {
                    throw new InvalidCommand("This command should have exactly 1 argument.");
                } else if (!isInteger(wordList.get(1))) {
                    throw new InvalidCommand("The argument MUST contain a single integer.");
                } else {
                    int currTaskId = Integer.parseInt(wordList.get(1));
                    if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                        taskListOfTasks.mark(currTaskId); // Valid taskID, proceed to mark task
                    } else {
                        throw new InvalidIndex("The specified task ID is out of range. " +
                                "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                    }
                }
                break;
            case("unmark"):
                if (wordList.size() != 2) {
                    throw new InvalidCommand("This command should have exactly 1 argument");
                } else if (!isInteger(wordList.get(1))) {
                    throw new InvalidCommand("The argument MUST contain a single integer.");
                } else {
                    int currTaskId = Integer.parseInt(wordList.get(1));
                    if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                        taskListOfTasks.unmark(currTaskId); // Valid taskID, proceed to unmark task
                    } else {
                        throw new InvalidIndex("The specified task ID is out of range. " +
                                "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                    }
                }
                break;
            case("delete"):
                if (wordList.size() != 2) {
                    throw new InvalidCommand("This command should have exactly 1 argument");
                } else if (!isInteger(wordList.get(1))) {
                    throw new InvalidCommand("The argument MUST contain a single integer.");
                } else {
                    int currTaskId = Integer.parseInt(wordList.get(1));
                    if (currTaskId > 0 & currTaskId <= taskListOfTasks.getNumberOfTasks()) {
                        taskListOfTasks.delete(currTaskId); // Valid taskID, proceed to unmark task
                    } else {
                        throw new InvalidIndex("The specified task ID is out of range. " +
                                "Please enter a number from 0 to " + taskListOfTasks.getNumberOfTasks() + ".");
                    }
                }
                break;
            default:
                throw new InvalidCommand("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Checks if the input date is in the correct format.
     *
     * @param date Date that is to be tested.
     */
    private static LocalDate convertDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter date in this format: <yyyy-MM-dd>");
        }
        return convertedDate;
    }

    private static LocalTime convertTime(String time) {
        if (time == null) { // Time is optional
            return null;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        LocalTime convertedTime;
        try {
            convertedTime = LocalTime.parse(time, format);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new InvalidDateTime("Please enter time in this 24hrs-format: <HHmm>");
        }
        return convertedTime;
    }
    public static void main(String[] args) {
        greet();
        taskListOfTasks = new TaskList(100); // Assume there will be no more than 100 tasks
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                parse(userInput);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                userInput = sc.nextLine();
            }
        }
        sc.close();
        exit();
    }
}
