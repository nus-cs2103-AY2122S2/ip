package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import tasks.TaskList;

/**
 * Represents a command that allows users to unmark tasks in the task list
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
     * Method that executes a command to unmark a task in the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a task has been unmarked
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            return TaskList.markTask(taskId, false);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
