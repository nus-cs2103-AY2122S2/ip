package duke.command;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Objects;

/**
 * The type Delete all command.
 */
public class DeleteAllCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            taskList.taskSet.clear();
            taskList.tasksArrayList.clear();
            storage.writeFile(taskList);
            ui.deletedAll();
        } catch (Exception e) {
            throw new CortanaException("Something went wrong when attempting to delete all tasks.");
        }
    }

    /**
     * The program is not yet exited.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            return obj instanceof DeleteAllCommand;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
