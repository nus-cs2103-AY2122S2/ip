package duke.command;

import java.io.IOException;

import duke.List;
import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a DeleteCommand which tells duke.Duke to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a delete command.
     * @param index Index of the task to be deleted.
     */
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
     * @returns Returns a String reply to the user.
     * @throws IOException If File to be written to in Storage is not found.
     * @throws DukeException If task to be deleted is not found in the list.
     */
    @Override
    public String execute(List taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task task = taskList.delete(index);
        storage.writeToFile("data/duke.txt", taskList);
        return ui.showDeletedTask(taskList, task);
    }
}
