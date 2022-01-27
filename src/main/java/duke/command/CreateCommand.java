package duke.command;

import java.time.DateTimeException;
import java.util.List;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.TodoTask;
import duke.Ui;

import duke.Storage;

/**
 * Command to create new tasks.
 */
public class CreateCommand extends Command {

    private static final String MESSAGE_TASKADD = "Got it. I've added this task:";

    private static final String ERROR_INVALID_TITLE = "OOPS!!! The title of a task cannot be empty :(";

    private static final String ERROR_EMPTY_DEADLINETASK_DEADLINE = "OOPS!!! The deadline cannot be " +
            "empty :( Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";
    private static final String ERROR_EMPTY_EVENT_TIME = "OOPS!!! The time of an event cannot be " +
            "empty :( Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";

    private static final String ERROR_INVALID_TIME = "OOPS!!! The time is in the wrong format :( " +
            "Enter date in the format of yyyy-mm-dd hh:mm or yyyy-mm-dd";

    private String args;
    private TaskType taskType;

    /**
     * Constructor to the create command.
     *
     * @param args Arguments needed in the line of the task to create
     * @param taskType Type of task to create
     * @throws DukeException If the arguments is an empty string
     */
    public CreateCommand(String args, TaskType taskType) throws DukeException {
        if (args.equals("")) {
            throw new DukeException(ERROR_INVALID_TITLE);
        }
        this.args = args;
        this.taskType = taskType;
    }

    /**
     * Execution of the create command to create corresponding tasks.
     *
     * @param tasks Task list
     * @param ui UI object
     * @throws DukeException If there are incompatibilities or errors in the format for creating task
     */
    @Override
    public void execute(List<Task> tasks, Ui ui) throws DukeException {
        Task newTask = null;
        if (taskType == TaskType.TODO) {
            newTask = new TodoTask(this.args);
            tasks.add(newTask);
        } else if (taskType == TaskType.DEADLINE) {
            if (!args.contains("/by")) {
                throw new DukeException(ERROR_EMPTY_DEADLINETASK_DEADLINE);
            }
            String[] subSplit = args.split("/by");
            if (subSplit.length == 1) {
                throw new DukeException(ERROR_EMPTY_DEADLINETASK_DEADLINE);
            }
            String[] dateTimeSplit = subSplit[1].substring(1).split(" ");
            try {
                if (dateTimeSplit.length == 1) {
                    newTask = new DeadlineTask(subSplit[0].trim(), dateTimeSplit[0]);
                } else if (dateTimeSplit.length == 2) {
                    newTask = new DeadlineTask(subSplit[0].trim(), dateTimeSplit[0], dateTimeSplit[1]);
                } else {
                    throw new DukeException(ERROR_INVALID_TIME);
                }
            } catch (DateTimeException e) {
                throw new DukeException(ERROR_INVALID_TIME);
            }
            tasks.add(newTask);        
        } else if (taskType == TaskType.EVENT) {
            if (!args.contains("/at")) {
                throw new DukeException(ERROR_EMPTY_EVENT_TIME);
            }
            String[] subSplit = args.split("/at");
            if (subSplit.length == 1) {
                throw new DukeException(ERROR_EMPTY_EVENT_TIME);
            }
            String[] dateTimeSplit = subSplit[1].substring(1).split(" ");
            try {
                if (dateTimeSplit.length == 1) {
                    newTask = new EventTask(subSplit[0].trim(), dateTimeSplit[0]);
                } else if (dateTimeSplit.length == 2) {
                    newTask = new EventTask(subSplit[0].trim(), dateTimeSplit[0], dateTimeSplit[1]);
                } else {
                    throw new DukeException(ERROR_INVALID_TIME);
                }
            } catch (DateTimeException e) {
                throw new DukeException(ERROR_INVALID_TIME);
            }
            tasks.add(newTask);
        }
        Storage.saveToFile(tasks);
        ui.printAddDeleteTaskSuccess(tasks, newTask, MESSAGE_TASKADD);
    }
}
