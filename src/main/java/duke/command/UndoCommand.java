package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;

/**
 * Represents a command that undoes the last command.
 */
public class UndoCommand extends Command {
    /**
     * Undoes the last command by retrieving the latest copy of the task list from the stack of
     * task lists.
     *
     * @param taskList List of tasks to be replaced.
     * @param ui       UI responsible for displaying response from Duke.
     * @param storage  Storage responsible for reading and writing to local file.
     * @return String response from Duke upon successful execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) throws DukeException {
        String output;
        TaskList latestTaskList = taskStack.pop();

        if (latestTaskList != null) {
            taskList.copy(latestTaskList);
            output = "LAST TASK UNDONE. CURRENT TASKS:" + taskList;
            storage.writeToFile(taskList);
        } else {
            output = "CAN'T UNDO FURTHER.";
        }

        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
