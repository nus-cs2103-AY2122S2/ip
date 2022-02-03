package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Add deadline command.
 *
 * <p>Extends from TaskCommand, as this class adds new Events task
 * to the task list</>
 */
public class DeadlineCommand extends TaskCommand {
    private static final String TASK_FORMAT_WRONG
            = "Did you remember to put in the deadline after /by? Or did u remember to add /by?";
    private static final String DATE_FORMAT_WRONG = "Date format maybe wrong. yy-mm-dd";
    private static final String TIME_FORMAT_WRONG = "Time format wrong. HHmm";

    /**
     * Constructor for command to init values.
     *
     * <p>Calls superclass TaskCommand constructor.</>
     *
     * @param key Keyword to call this command.
     */
    public DeadlineCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the add deadline command.
     *
     * <p>Create and add new deadline task into the task list base on what the user inputs.
     * Will also print the new deadline task created through the UI. <br>
     * User does not have to put in a time, default time set is 2359.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Deadline response.
     * @throws DukeException If no task descriptor or
     * deadline task format is wrong (no /by)
     * or date format is wrong (yy-mm-dd)
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

        // parse the date in yy-mm-dd
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateTime[0]);
        } catch (DateTimeParseException error) {
            throw new DukeException(DATE_FORMAT_WRONG);
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
            throw new DukeException(TIME_FORMAT_WRONG);
        }

        newTask = new Deadline(statement, localDate, localTime);
        return updateTaskList(newTask, taskList, storage);
    }
}