package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import tasks.TaskList;

/**
 * Represents a command that allows users to mark tasks in the task list
 */
public class Mark extends Command {
    private final Integer taskId;

    /**
     * Initialize a Mark Command
     * @param taskId index of the task to be marked
     */
    public Mark(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * Method that executes a command to mark a task in the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a task has been marked
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            return taskList.markTask(taskId, true);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Method that undoes a mark command
     * @param taskList tasks stored in Duke
     * @return message after a mark command has been undone
     */
    @Override
    public String undo(TaskList taskList) {
        try {
            return taskList.markTask(taskId, false);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

