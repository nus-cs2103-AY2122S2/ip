import java.io.IOException;

/**
 * Extends Command class
 * When executed, calls ui method to print relevant output
 */
public class UnmarkCommand extends Command {
    int ranking;

    /**
     * Constructor
     * Initialise index of task to mark as not done
     * @param ranking int ranking of task
     */
    public UnmarkCommand(int ranking) {
        this.ranking = ranking;
    }

    /**
     * Handles un-marking of task of specific index in tasklist as not done
     * @param tasklist TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String output from ui
     * @throws IOException
     */
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws IOException {
        assert this.ranking > 0 && this.ranking <= tasklist.getLength() : "Please give a valid input (0 < input < number of tasks";

        tasklist.markUndone(this.ranking);
        storage.writeToFile(tasklist);
        return ui.printUnmarkTaskFromDone(tasklist, this.ranking);
    }
}