package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Command to edit the task to be either marked or unmarked.
 */
public class EditTaskMarkCommand extends Command {
    private final static String NO_INPUT = "You need to put the task number you want to delete!";
    private final static String INVALID_INPUT = "Invalid input, you need to give a number/integer";
    private final static String MARK_TASK = "Nice I've marked this task as done: \n";
    private final static String UNMARK_TASK = "Alright, I've unmarked the task: \n ";
    private final boolean isMarkDoneTask;

    /**
     * Edit Task Mark Command constructor.
     *
     * @param key Keyword to call this command.
     * @param isMarkDoneTask Whether to mark or unmark a task as done.
     */
    public EditTaskMarkCommand(String key, boolean isMarkDoneTask) {
        super(key);

        this.isMarkDoneTask = isMarkDoneTask;
    }

    /**
     * Execution behavior of the EditTaskMark command.
     *
     * <p>Based on the task number given, will check whether the task exists.
     * If the task exists, will mark or unmark it.
     * Afterwards, print out the updated task using the UI.</>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @param ui Duke UI to print what the command wants.
     * @throws DukeException If no task description or invalid input (User must write the task number in int) or task index does not exist.
     */
    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        int taskIndex = 0;
        String taskDesc = getTaskDescription(input, NO_INPUT);

        try {
            taskIndex = Integer.parseInt(taskDesc) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException(INVALID_INPUT);
        }

        Task updatedTask = taskList.markTask(taskIndex, isMarkDoneTask);
        String cmdDescription = isMarkDoneTask ? MARK_TASK : UNMARK_TASK;

        ui.printResponse(cmdDescription + updatedTask.toString());
    }
}
