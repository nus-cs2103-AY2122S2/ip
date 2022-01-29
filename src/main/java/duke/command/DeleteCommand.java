package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;

import duke.task.Task;

import java.io.IOException;

/**
 * Represents a DeleteCommand which tells Duke to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     *
     * @param taskList TaskList of current Tasks.
     * @param ui Ui.
     * @param storage Storage.
     * @throws IOException If File to be written to in Storage is not found.
     * @throws DukeException If task to be deleted is not found in the list.
     */
    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task task = taskList.delete(index);
        ui.printDeletedTask(taskList, task);
        storage.writeToFile("data/duke.txt", taskList);
    }
}
