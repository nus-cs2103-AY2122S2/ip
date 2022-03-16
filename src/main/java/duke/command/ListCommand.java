package duke.command;

import duke.ui.Ui;
import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents a list command in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class ListCommand extends Command {

    /**
     * Execute the command to print the tasks in task list.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     * @throws DukeException There exist no task in the task list.
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.MSG_EMPTYTASK);
        } else {
            return Ui.taskListMsg(taskList);
        }
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return "To list all task(s), use the list command.\n"
                + "  Usage: list\n\n";

    }
}
