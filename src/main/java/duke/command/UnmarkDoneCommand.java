package duke.command;

import java.io.IOException;

import duke.List;
import duke.Storage;
import duke.Ui;


/**
 * Represents an UnmarkDoneCommand which marks a done task as not done.
 */
public class UnmarkDoneCommand extends Command {

    private int index;

    /**
     * Constructs an unmark done command.
     * @param index Index of task to be unmarked.
     */
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
    public String execute(List taskList, Ui ui, Storage storage) throws IOException {
        taskList.unmarkDone(index);
        storage.writeToFile("data/duke.txt", taskList);
        return ui.showUnmarkDoneTask(taskList.getArrayList().get(index));
    }
}