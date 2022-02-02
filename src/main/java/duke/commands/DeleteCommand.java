package duke.commands;

import duke.DukeList;
import duke.UI;

public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Constructor
     *
     * @param idx index of the task
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     */
    @Override
    public void execute(DukeList dukeList, UI ui) {
        ui.printMsg(dukeList.delete(idx));
    }
}
