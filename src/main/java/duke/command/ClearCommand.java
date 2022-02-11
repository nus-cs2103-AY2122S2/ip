package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;

/**
 * Represents a command that clears a list of tasks.
 *
 * @author Peter
 */
public class ClearCommand extends Command {
    /**
     * Clears a given list of tasks, displays a response message, and truncates a local file
     * associated with the list of tasks.
     *
     * @param taskList  List of tasks that is to be cleared.
     * @param ui        UI responsible for displaying the response message.
     * @param storage   Storage responsible for truncating local file.
     * @param taskStack Stack of tasks to use with undo command.
     * @return <code>true</code> upon successful execution.
     * @throws DukeException If file truncation is unsuccessful.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) throws DukeException {
        TaskList copiedTaskList = new TaskList();
        copiedTaskList.copy(taskList);
        taskStack.push(copiedTaskList);
        storage.clearFile();
        taskList.clear();
        String output = "ALL TASKS CLEARED";
        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
