import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String ERROR_UNKNOWN = "OOPS, I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_DESCRIPTION = "OOPS, The description of a command cannot be empty.";
    private static final String ERROR_FORMAT_DATE = "Wrong Format... Try yyyy-MM-dd HHmm";

    public static void parse(String input, TaskList taskList) throws DukeException {
        try {
            if (input.equals("list")) {
                taskList.listItems();
            } else if (input.startsWith("mark")) {
                String taskNumber = input.substring(5);
                taskList.markTask(taskNumber);
            } else if (input.startsWith("unmark")) {
                String taskNumber = input.substring(7);
                taskList.unmarkTask(taskNumber);
            } else if (input.startsWith("todo")) {
                String description = input.substring(5);
                taskList.addToDoTask(description);
            } else if (input.startsWith("deadline")) {
                String[] descriptionArr = input.substring(9).split(" /by ");
                taskList.addDeadlineTask(descriptionArr);
            } else if (input.startsWith("event")) {
                String[] descriptionArr = input.substring(6).split(" /at ");
                taskList.addEventTask(descriptionArr);
            } else if (input.startsWith("delete")) {
                String taskNumber = input.substring(7);
                taskList.deleteTask(taskNumber);
            } else {
                throw new DukeException(ERROR_UNKNOWN);
            }
        } catch(IndexOutOfBoundsException error) {
            throw new DukeException(ERROR_DESCRIPTION);
        }
    }

    public static LocalDate convertDate(String dateString) throws DukeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDate.parse(dateString, format);
        } catch (DateTimeParseException error) {
            throw new DukeException(ERROR_FORMAT_DATE);
        }
    }
}
