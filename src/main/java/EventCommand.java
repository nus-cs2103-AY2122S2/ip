import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends TaskCommand {
    private final static String TASK_FORMAT_WRONG = "Did you remember to put in the date after /at? Or did u remember to add /at?";
    private final static String DATE_FORMAT_WRONG = "Event Date format is wrong. yy-mm-dd";
    private final static String TIME_FORMAT_WRONG = "Event time format is wrong. HHmm";

    public EventCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask = null;
        String taskDescription = getTaskDescription(input);

        String statement = "";
        String[] dateTime;
        try {
            statement = taskDescription.substring(0, taskDescription.indexOf(" /by"));
            dateTime = taskDescription.substring(taskDescription.indexOf("/by") + 4).split(" ");
        } catch (StringIndexOutOfBoundsException error) {
            throw new DukeException(TASK_FORMAT_WRONG);
        }

        // parse the date in yy-mm-dd
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException error) {
            throw new DukeException(TIME_FORMAT_WRONG);
        }

        // parse the time in military format 0000
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime localTime = LocalTime.parse("2359", formatter);
        try {
            // check if user input a time, if not use default time
            if (dateTime.length > 1) {
                localTime = LocalTime.parse(dateTime[1], formatter);
            }
        } catch (DateTimeParseException error) {
            throw new DukeException(DATE_FORMAT_WRONG);
        }

        newTask = new Event(false, statement, localDate, localTime);
        updateTaskList(newTask, taskList, storage, ui);
    }
}
