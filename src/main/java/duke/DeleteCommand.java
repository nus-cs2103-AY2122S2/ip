package duke;
import exceptions.DukeException;

import java.io.IOException;

/**
 * Extends Command class
 * When executed, calls ui to print delete output
 */
public class DeleteCommand extends Command {
    int ranking;
    public DeleteCommand(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Handles deleting of a specific task by its index
     * Calls ui to print output for delete action and updates storage
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return void
     * @throws IOException
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        try {
            assert ranking > 0 && ranking <= tasklist.getLength();
        } catch (AssertionError e) {
            throw new DukeException("Please input a valid index between 1 to " + tasklist.getLength());
        }

        tasklist.delete(ranking);
        storage.writeToFile(tasklist);
        ui.printTaskIsDeleted(tasklist);
    }
}
