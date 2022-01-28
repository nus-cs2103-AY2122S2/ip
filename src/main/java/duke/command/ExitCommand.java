package duke.command;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the exit command.
     */
    public ExitCommand(){

    }

    /** {@inheritDoc} */
    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        ui.close();
        return false;
    }
}
