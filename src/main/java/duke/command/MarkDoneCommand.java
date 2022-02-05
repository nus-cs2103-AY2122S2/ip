package duke.command;

import java.io.IOException;

import duke.List;
import duke.Storage;
import duke.Ui;

/**
 * Represents a MarkDoneCommand which tells duke.Duke to mark a task as done.
 */
public class MarkDoneCommand extends Command {

    private int index;

    /**
     * Constructs a mark done command.
     * @param index Index of the task to be marked done.
     */
    public MarkDoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the MarkDoneCommand.
     *
     * @param taskList TaskList of current tasks.
     * @param ui Ui.
     * @param storage Storage.
     * @throws IOException If the File to be written to in Storage is not found.
     */
    @Override
    public String execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.markDone(index);
        storage.writeToFile("data/duke.txt", taskList);
        return ui.showMarkDoneTask(taskList.getArrayList().get(index));
    }
}
