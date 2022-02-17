package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents a command that allows users to mark a task at a
 * specific index in the task list.
 */
public class Mark extends Command {
    private final Integer taskId;

    /**
     * Initialize a Mark Command.
     *
     * @param taskId Index of the task to be marked.
     */
    public Mark(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns a string after a task has successfully been marked as completed
     * in the task list.
     * Returns an error message if there were issues faced when marking
     * a task as completed in task list.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return Message after a task has successfully been marked as completed.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            return TaskList.markTask(taskId, true);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a success message after a successful undo of a mark Command.
     * The task list will be reverted to the state where the task was unmarked.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Message after a mark command has been successfully undone.
     */
    @Override
    public String undo(TaskList taskList) {
        try {
            return TaskList.markTask(taskId, false);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

