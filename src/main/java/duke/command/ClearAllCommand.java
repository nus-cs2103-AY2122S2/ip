package duke.command;

import java.util.Objects;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The type Delete all command.
 */
public class ClearAllCommand extends Command {

    /**
     * Clear all tasks in the task list.
     *
     * @param taskList the task list to operate on
     * @param ui the ui to operate on
     * @param storage the storage to operate on
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            taskList.getTaskSet().clear();
            taskList.getTaskList().clear();
            assert taskList.getTaskSet().isEmpty() && taskList.getTaskList().isEmpty();
            storage.writeFile(taskList);
            assert storage.loadFile().isEmpty();
            return ui.clearAll();
        } catch (Exception e) {
            throw new CortanaException("Something went wrong when attempting to clear all tasks.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return obj instanceof ClearAllCommand;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
