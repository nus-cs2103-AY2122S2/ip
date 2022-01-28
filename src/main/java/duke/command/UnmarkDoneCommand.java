package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
import java.io.IOException;

/**
 * Represents an UnmarkDoneCommand which marks a done task as not done.
 */
public class UnmarkDoneCommand extends Command {
    private int index;

    public UnmarkDoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the UnmarkDoneCommand.
     *
     * @param taskList TaskList of the current tasks.
     * @param ui Ui.
     * @param storage Storage.
     * @throws IOException If the File to be written to in Storage is not found.
     */
    @Override
    public void execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.unmarkDone(index);
        ui.printUnmarkDoneTask(taskList.getArrayList().get(index));
        storage.writeToFile("data/duke.txt", taskList);
    }
}