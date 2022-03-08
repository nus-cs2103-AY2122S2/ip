package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Command to edit the task to be either marked or unmarked.
 */
public class EditTaskMarkCommand extends Command {
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    private static final String NO_INPUT = "You need to put the task number you want to delete!";
    private static final String INVALID_INPUT = "Invalid input, you need to give a number/integer";
    private static final String MARK_TASK = "Nice I've marked this task as done: \n";
    private static final String UNMARK_TASK = "Alright, I've unmarked the task: \n ";
    private final boolean isMarkDoneTask;

    /**
     * Edit Task Mark Command constructor.
     *
     * @param isMarkDoneTask Whether to mark or unmark a task as done.
     */
    public EditTaskMarkCommand(boolean isMarkDoneTask) {
        super(isMarkDoneTask ? MARK_COMMAND : UNMARK_COMMAND);

        this.isMarkDoneTask = isMarkDoneTask;
    }

    /**
     * Execution behavior of the EditTaskMark command.
     *
     * <p>Based on the task number given, will check whether the task exists.
     * If the task exists, will mark or unmark it.
     * Afterwards, print out the updated task using the UI.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Edited task description.
     * @throws DukeException If:
     * - No task description.
     * - Invalid input (User must write the task number in int).
     * - Task index does not exist.
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        int taskIndex = 0;
        String taskDesc = getTaskDescription(input, NO_INPUT);

        try {
            taskIndex = Integer.parseInt(taskDesc) - 1;
        } catch (NumberFormatException error) {
            throw new DukeException(INVALID_INPUT);
        }

        Task updatedTask = taskList.markTask(taskIndex, isMarkDoneTask);
        String cmdDescription = isMarkDoneTask ? MARK_TASK : UNMARK_TASK;
        String printStatement = cmdDescription + updatedTask.toString();

        try {
            storage.saveList(taskList.getTaskList());
        } catch (DukeException exception) {
            printStatement = exception.getMessage() + "\n" + printStatement;
        }

        return printStatement;
    }
}
