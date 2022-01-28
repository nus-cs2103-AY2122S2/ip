package duke;
import java.io.IOException;

/**
 * Extends Command class
 * When executed, calls ui method to print relevant output
 */
public class MarkCommand extends Command {
    int ranking;

    /**
     * Constructor
     * Initialise index of task to mark as done
     * @param ranking int ranking of task
     */
    public MarkCommand(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Handles marking of task of specific index in tasklist as done
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String output from ui
     * @throws IOException
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        assert ranking > 0 && ranking <= tasklist.getLength() : "Please give a valid input (0 < input < number of tasks)";

        tasklist.markDone(ranking);
        storage.writeToFile(tasklist);
        return ui.printMarkTaskAsDone(tasklist, ranking);
    }
}
