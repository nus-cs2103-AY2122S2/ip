package duke;
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
     * @return String output from ui
     * @throws IOException
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        assert ranking > 0 && ranking <= tasklist.getLength() : "Please give a valid input (0 < input < number of tasks";

        tasklist.delete(ranking);
        storage.writeToFile(tasklist);
        return ui.printTaskIsDeleted(tasklist);
    }
}
