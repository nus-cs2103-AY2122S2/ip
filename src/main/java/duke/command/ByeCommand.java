package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

/**
 * Represents a ByeCommand which exits duke.Duke.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a bye command. Set isExit to true.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the ByeCommand and exits duke.Duke.
     *
     * @param taskList TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return Returns a String reply to user.
     */
    @Override
    public String execute(List taskList, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }
}
