package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

/**
 * Represents a ByeCommand which exits Duke.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the ByeCommand and exits Duke.
     *
     * @param taskList TaskList.
     * @param ui Ui.
     * @param storage Storage.
     */
    @Override
    public void execute(List taskList, Ui ui, Storage storage) {
        ui.byeUser();
    }
}
