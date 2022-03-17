package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Add deadline command.
 *
 * <p>Extends from TaskCommand, as this class adds new Events task
 * to the task list. </p>
 */
public class DeadlineCommand extends TaskCommand {
    public static final String DEADLINE_COMMAND = "deadline";

    private static final String TASK_FORMAT_WRONG =
            "Did you remember to put in the deadline after /by? Or did u remember to add /by?";
    private static final String DATE_FORMAT_WRONG = "Date format maybe wrong. yyyy-mm-dd";
    private static final String TIME_FORMAT_WRONG = "Time format wrong. HHmm";

    private static final String INPUT_TIME_FORMAT = "HHmm";
    private static final String DEFAULT_TIME = "2359";

    /**
     * Constructor for command to init values.
     *
     * <p>Calls superclass TaskCommand constructor.</p>
     */
    public DeadlineCommand() {
        super(DEADLINE_COMMAND);
    }

    /**
     * Execution behavior of the add deadline command.
     *
     * <p>Create and add new deadline task into the task list base on what the user inputs.
     * Will also print the new deadline task created through the UI. <br>
     * User does not have to put in a time, default time set is 2359.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Deadline response.
     * @throws DukeException If no task descriptor or
     * deadline task format is wrong (no /by)
     * or date format is wrong (yyyy-mm-dd)
     * or time format is wrong (HHmm)
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
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

        // parse the date in yyyy-mm-dd
        LocalDate localDate = parseDateFromStr(dateTime[0]);

        // check if user input a time, if not use default time
        LocalTime localTime;
        if (dateTime.length > 1) {
            localTime = parseTimeFromStr(dateTime[1]);
        } else {
            localTime = parseTimeFromStr(DEFAULT_TIME);
        }

        newTask = new Deadline(statement, localDate, localTime);
        return updateTaskList(newTask, taskList, storage);
    }

    private LocalDate parseDateFromStr(String date) throws DukeException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException error) {
            throw new DukeException(DATE_FORMAT_WRONG);
        }

        return localDate;
    }

    private LocalTime parseTimeFromStr(String time) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_TIME_FORMAT);
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(time, formatter);
        } catch (DateTimeParseException error) {
            throw new DukeException(TIME_FORMAT_WRONG);
        }

        return localTime;
    }
}
