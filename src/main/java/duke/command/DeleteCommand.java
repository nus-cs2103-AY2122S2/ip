package duke.command;
import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(index);
        storage.saveUpdatedTask(index, null);
        return true;
    }
}
