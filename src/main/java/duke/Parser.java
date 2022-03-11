package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeEmptyTaskDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIncorrectDateFormat;


public class Parser {

    /**
     * splits the date and time
     * @param description the task description
     * @return array containing the sate and time split
     */
    public static String[] splitDateAndTime(String description) {
        assert !description.equals("");
        return description.split(" ", 2);
    }

    /**
     * 
     * @param description the task description
     * @return time mentioned in the task description
     */
    public static String getTime(String description) {
        if (splitDateAndTime(description).length == 1) {
            return "";
        }
        return splitDateAndTime(description)[1].trim();
    }

    /**
     * 
     * @param description the task description
     * @return the date of the task
     * @throws DateTimeParseException
     */

    public static String getDate(String description) throws DukeIncorrectDateFormat{
        try {
            LocalDate localDate = LocalDate.parse(splitDateAndTime(description)[0].trim());
            return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeIncorrectDateFormat();
        }
    }

    /**
     * 
     * @param taskStatement the task description
     * @return string array containing the main description and the peripheral information about date and time
     */

    public static String[] splitTaskStatement(String taskStatement) {
        String[] splitted = taskStatement.split("/", 2);
        splitted[1] = splitted[1].trim().split(" ", 2)[1];
        return splitted;
    }

    public static String getCommand(String taskStatement) {
        String[] splitted = taskStatement.split(" ", 2);
        return splitted[0];
    }

    /**
     * 
     * @param taskStatement the input given
     * @return the description of the given input
     * @throws DukeEmptyTaskDescriptionException
     */

    public static String getTaskDescription(String input) throws DukeEmptyTaskDescriptionException {
        String[] splitted = input.split(" ", 2);
        String command = getCommand(input);
        if (command.equals("list") || command.equals("bye")) {
            return "";
        } else if (splitted.length == 1) {
            throw new DukeEmptyTaskDescriptionException();
        }
        return splitted[1];
    }
    /**
     * 
     * @param taskStatement the task description
     * @return the string containing the main description
     */

    public static String getDescription(String taskStatement) {
        return splitTaskStatement(taskStatement)[0];
    }


    /**
     * 
     * @param taskStatement the task description
     * @return get the date and time related to the task
     */

    public static String getDateAndTime(String taskStatement) throws DukeException{
        String description = splitTaskStatement(taskStatement)[1];
        return getDate(description) + " " + getTime(description);
    }
}

