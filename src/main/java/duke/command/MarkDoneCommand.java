package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

import java.io.IOException;

/**
 * Represents a MarkDoneCommand which tells duke.Duke to mark a task as done.
 */
public class MarkDoneCommand extends Command {

    private int index;

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
        return ui.printMarkDoneTask(taskList.getArrayList().get(index));
    }
}
