package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents a command that allows users to unmark duke.tasks in the task list
 */
public class Unmark extends Command {
    private final Integer taskId;

    /**
     * Initialize an unmark Command
     * @param taskId index of the task to be marked
     */
    public Unmark(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Returns a string after a task has successfully been unmarked as not completed
     * in the task list.
     * Returns an error message if there were issues faced when un-marking
     * a task as not completed in task list.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return Message after a task has successfully been unmarked as not completed.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            return TaskList.markTask(taskId, false);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a success message after a successful undo of a Unmark Command.
     * The task list will be reverted to the state where the task was marked.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Message after an un-mark command has been successfully undone.
     */
    @Override
    public String undo(TaskList taskList) {
        try {
            return TaskList.markTask(taskId, true);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
