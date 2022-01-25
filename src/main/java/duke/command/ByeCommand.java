package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {

    /**
     * Constructs a {@code ByeCommand} object with keyword BYE
     */
    public ByeCommand() {
        super(Keyword.BYE);
    }

    /**
     * Pushes a goodbye message to the UI.
     * @param tasks current list of tasks
     * @param ui the UI used
     * @param storage the storage used
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

}
