package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a list command in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class ListCommand extends Command{

    /**
     * Execute the command to print the tasks in task list.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException There exist no task in the task list.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(ui.MSG_EMPTYTASK);
        } else {
            ui.print(ui.taskListMsg(taskList));
        }
    }
}
