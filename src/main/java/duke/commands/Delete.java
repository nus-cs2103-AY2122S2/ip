package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import tasks.TaskList;

/**
 * Represents a command that allows users to delete a task from the task list
 */
public class Delete extends Command {
    private final Integer taskId;

    /**
     * Initialize a Delete Command
     * @param taskId the index of the task to be deleted
     */
    public Delete(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Method that executes a command to delete a task from the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a task has successfully been deleted from the tasklist
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            return TaskList.deleteTask(taskId);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
