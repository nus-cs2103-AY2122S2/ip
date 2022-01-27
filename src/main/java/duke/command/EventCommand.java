package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Add event command.
 *
 * <p>Extends from TaskCommand, as this class adds new Events task
 * to the task list</>
 */
public class EventCommand extends TaskCommand {
    private final static String TASK_FORMAT_WRONG = "Did you remember to put in the date after /at? Or did u remember to add /at?";
    private final static String DATE_FORMAT_WRONG = "duke.task.Event Date format is wrong. yy-mm-dd";
    private final static String TIME_FORMAT_WRONG = "duke.task.Event time format is wrong. HHmm";

    /**
     * Constructor for event command to init values.
     *
     * <p>Calls superclass TaskCommand constructor.</>
     *
     * @param key Keyword to call this command.
     */
    public EventCommand(String key) {
        super(key);
    }

    /**
     * Execution behavior of the add event command.
     *
     * <p>Create and add new event task into the task list base on what the user inputs.
     * Will also print the new event task created through the UI.<br>
     * User does not have to put in a time, default time set is 2359.</>
     *
     * @param input User input.
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException If no task descriptor or
     * event task format is wrong (no /at)
     * or date format is wrong (yy-mm-dd)
     * or time format is wrong (HHmm)
     */
    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask = null;
        String taskDescription = getTaskDescription(input);

        String statement = "";
        String[] dateTime;
        try {
            statement = taskDescription.substring(0, taskDescription.indexOf(" /at"));
            dateTime = taskDescription.substring(taskDescription.indexOf("/at") + 4).split(" ");
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

        newTask = new Event(statement, localDate, localTime);
        updateTaskList(newTask, taskList, storage, ui);
    }
}
