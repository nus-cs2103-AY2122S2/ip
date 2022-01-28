package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task task = taskList.delete(index);
        ui.printDeletedTask(taskList, task);
        storage.writeToFile("data/duke.txt", taskList);
    }
}
