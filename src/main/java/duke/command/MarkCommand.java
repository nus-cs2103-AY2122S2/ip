package duke.command;

import duke.exception.DukeException;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import duke.task.Task;

public class MarkCommand extends Command {

    int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.mark(index);
        storage.saveUpdatedTask(index, markedTask);
        return true;
    }
}
