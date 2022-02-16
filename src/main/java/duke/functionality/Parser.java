package duke.functionality;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;



/**
 * Parser is a functionality class that helps Duke to understand what the user inputs are.
 */
public class Parser {
    public static final String ERROR_UNKNOWN = "I don't know what that means! Command does not exist or your format is wrong!";
    public static final String ERROR_DESCRIPTION = "OOPS, The description of a command cannot be empty.";
    public static final String ERROR_MISSING_DATE = "OOPS, check again if you have the date and the description.";
    public static final String ERROR_FORMAT_DATE = "Wrong Format... Try yyyy-mm-dd";
    public static final String ERROR_TASK_NOT_EXIST = "Task does not exist...";
    private static final int INDEX_AFTER_MARK_COMMAND = 5;
    private static final int INDEX_AFTER_UNMARK_COMMAND = 7;
    private static final int INDEX_AFTER_TODO_COMMAND = 5;
    private static final int INDEX_AFTER_DEADLINE_COMMAND = 9;
    private static final int INDEX_AFTER_EVENT_COMMAND = 6;
    private static final int INDEX_AFTER_DELETE_COMMAND = 7;
    private static final int INDEX_AFTER_FIND_COMMAND = 5;
    /**
     * Adds a specific type of task into the tasklist depending on the user input.
     *
     * @param input String user input to be parsed.
     * @param taskList A tasklist to keep track of all the tasks.
     * @return A String output.
     * @throws DukeException If user input is not understood or description is empty.
     */
    public static String parse(String input, TaskList taskList) throws DukeException {
        String output = "";
        try {
            if (input.equals("list")) {
                output = taskList.listItems();
            } else if (input.startsWith("mark ")) {
                String taskNumber = input.substring(INDEX_AFTER_MARK_COMMAND);
                output = taskList.markTask(taskNumber);
            } else if (input.startsWith("unmark ")) {
                String taskNumber = input.substring(INDEX_AFTER_UNMARK_COMMAND);
                output = taskList.unmarkTask(taskNumber);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(INDEX_AFTER_TODO_COMMAND);
                output = taskList.addToDoTask(description);
            } else if (input.startsWith("deadline")) {
                String[] descriptionArr = input.substring(INDEX_AFTER_DEADLINE_COMMAND).split(" /by ");
                output = taskList.addDeadlineTask(descriptionArr);
            } else if (input.startsWith("event")) {
                String[] descriptionArr = input.substring(INDEX_AFTER_EVENT_COMMAND).split(" /at ");
                output = taskList.addEventTask(descriptionArr);
            } else if (input.startsWith("delete")) {
                String taskNumber = input.substring(INDEX_AFTER_DELETE_COMMAND);
                output = taskList.deleteTask(taskNumber);
            } else if (input.startsWith("find ")) {
                String description = input.substring(INDEX_AFTER_FIND_COMMAND);
                output = taskList.findTask(taskList, description);
            } else {
                throw new DukeException(ERROR_UNKNOWN);
            }
        } catch (StringIndexOutOfBoundsException error) {
            throw new DukeException(ERROR_DESCRIPTION);
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DukeException(ERROR_MISSING_DATE);
        } catch (NumberFormatException error) {
            throw new DukeException(ERROR_UNKNOWN);
        } catch (IndexOutOfBoundsException error) {
            throw new DukeException(ERROR_TASK_NOT_EXIST);
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
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateString, format);
        } catch (DateTimeParseException error) {
            throw new DukeException(ERROR_FORMAT_DATE);
        }
    }
}
