package jeff.parser;

import jeff.command.ByeCommand;
import jeff.command.Command;
import jeff.command.DeadlineCommand;
import jeff.command.DeleteCommand;
import jeff.command.EventCommand;
import jeff.command.FindCommand;
import jeff.command.HelpCommand;
import jeff.command.ListCommand;
import jeff.command.MarkCommand;
import jeff.command.TodoCommand;
import jeff.command.UnmarkCommand;
import jeff.main.JeffException;

/**
 * Parser class is used to parse the raw input from the user
 * and call the correct Command class while handling exceptions.
 */
public class Parser {
    private static boolean isExit = false;
    /**
     * Parses the user input and calls the correct command
     *
     * @param fullCommand Raw input from user.
     * @return Returns the command according to what the user wants.
     * @throws JeffException When certain information are missing, or formatting is wrong.
     */
    public static Command parse(String fullCommand) throws JeffException {
        String[] splitCommand = fullCommand.split(" ", 2);
        String keyword = splitCommand[0];
        String body = checkValidInfo(splitCommand);
        int len = splitCommand.length;

        // Perform the correct instructions according to the keyword.
        switch(keyword) {
        case ("bye"):
            isExit = true;
            return new ByeCommand();
        case ("list") :
            return new ListCommand();
        case ("mark"):
            checkValidFirstEntry(len, " ☹ OOPS!!! Please tell me the task's"
                    + " index number so that I can mark it as done.");
            return new MarkCommand(body);
        case ("unmark"):
            checkValidFirstEntry(len, " ☹ OOPS!!! Please tell me the task's"
                    + " index number so that I can mark it as not done.");
            return new UnmarkCommand(body);
        case ("todo"):
            checkValidFirstEntry(len, " ☹ OOPS!!! The description of a todo cannot be empty.");
            return new TodoCommand(body);
        case ("deadline"):
            checkValidFirstEntry(len, " ☹ OOPS!!! The description of a deadline cannot be empty.");

            String[] splitBody = body.split(" /by ", 2);
            String description = splitBody[0];
            String dateInfo = checkValidInfo(splitBody);
            int bodyLength = splitBody.length;

            checkValidDateTime(bodyLength, dateInfo, " ☹ OOPS!!! Please input a due date for this task");
            return new DeadlineCommand(description, dateInfo);
        case ("event"):
            checkValidFirstEntry(len, " ☹ OOPS!!! The description of a event cannot be empty.");

            splitBody = body.split(" /at ", 2);
            description = splitBody[0];
            dateInfo = checkValidInfo(splitBody);
            bodyLength = splitBody.length;

            checkValidDateTime(bodyLength, dateInfo, " ☹ OOPS!!! Please input a event date");
            return new EventCommand(description, dateInfo);
        case ("delete"):
            checkValidFirstEntry(len, " ☹ OOPS!!! Please tell me the task's"
                    + " index number so that I can delete it from the list.");
            return new DeleteCommand(body);
        case ("find"):
            checkValidFirstEntry(len, " ☹ OOPS!!! Please tell me the keyword"
                    + " so that I know what you are looking for.");
            return new FindCommand(body);
        default:
            return new HelpCommand();
        }
    }

    /**
     * Checks if the user did input a value for the body.
     *
     * @param length Length of the parsed command.
     * @param errorMessage Appropriate error message to throw.
     * @throws JeffException When user left out the body of the command.
     */
    private static void checkValidFirstEntry(int length, String errorMessage) throws JeffException {
        if (length == 1) {
            throw new JeffException(errorMessage);
        }
    }

    /**
     * Prevent NullPointerException when declaring a variable that doesn't exist.
     *
     * @param arr Contains information of the desired return value, if it exist.
     * @return Desired output if it exists.
     */
    private static String checkValidInfo(String[] arr) {
        if (arr.length > 1) {
            return arr[1];
        }
        return null;
    }

    /**
     * Checks if date and time information are given correctly.
     *
     * @param length Length of array containing date and time.
     * @param dateInfo String containing date and time information.
     * @param errorMessage Appropriate error message to throw.
     * @throws JeffException When the date time element does not exist, or means nothing.
     */
    private static void checkValidDateTime(int length, String dateInfo, String errorMessage) throws JeffException {
        if (length == 1 || dateInfo.equals("") || dateInfo.equals(" ")) {
            throw new JeffException(errorMessage);
        }
    }

    /**
     * Returns if exit is requested.
     *
     * @return Boolean value of isExit.
     */
    public static boolean isExit() {
        return isExit;
    }
}
