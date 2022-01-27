package duke.command;

import duke.ExitException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand} object.
     */
    public ByeCommand() {}

    /**
     * Pushes a goodbye message to the UI.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ExitException {
        ui.showMessage("Bye. Hope to see you again soon!");
        throw new ExitException();
    }

}
