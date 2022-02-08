package duke.functionality;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;



/**
 * Parser is a functionality class that helps Duke to understand what the user inputs are.
 */
public class Parser {
    public static final String ERROR_UNKNOWN = "OOPS, I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_DESCRIPTION = "OOPS, The description of a command cannot be empty.";
    public static final String ERROR_FORMAT_DATE = "Wrong Format... Try yyyy-MM-dd HHmm";

    /**
     * Adds a specific type of task into the tasklist depending on the user input.
     *
     * @param input String user input to be parsed.
     * @param taskList A tasklist to keep track of all the tasks.
     * @throws DukeException If user input is not understood or description is empty.
     */
    public static String parse(String input, TaskList taskList) throws DukeException {
        String output = "";
        try {
            if (input.equals("list")) {
                output = taskList.listItems();
            } else if (input.startsWith("mark")) {
                String taskNumber = input.substring(5);
                output = taskList.markTask(taskNumber);
            } else if (input.startsWith("unmark")) {
                String taskNumber = input.substring(7);
                output = taskList.unmarkTask(taskNumber);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                output = taskList.addToDoTask(description);
            } else if (input.startsWith("deadline")) {
                String[] descriptionArr = input.substring(9).split(" /by ");
                output = taskList.addDeadlineTask(descriptionArr);
            } else if (input.startsWith("event")) {
                String[] descriptionArr = input.substring(6).split(" /at ");
                output = taskList.addEventTask(descriptionArr);
            } else if (input.startsWith("delete")) {
                String taskNumber = input.substring(7);
                output = taskList.deleteTask(taskNumber);
            } else if (input.startsWith("find")) {
                String description = input.substring(5);
                output = taskList.findTask(taskList, description);
            } else {
                throw new DukeException(ERROR_UNKNOWN);
            }
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ERROR_DESCRIPTION);
        }
        return output;
    }

    /**
     * Returns a LocalDate object by converting a String date string.
     *
     * @param dateString A String version of the date.
     * @return LocalDate object.
     * @throws DukeException If formatting of the dateString is wrong.
     */
    public static LocalDate convertDate(String dateString) throws DukeException {
        try {
            System.out.println(dateString + "a");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, format);
        } catch (DateTimeParseException error) {
            throw new DukeException(ERROR_FORMAT_DATE);
        }
    }
}
