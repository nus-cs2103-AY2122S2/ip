package duke.command;
import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {

    int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmark(index);
        storage.saveUpdatedTask(index, unmarkedTask);
        return true;
    }
}
