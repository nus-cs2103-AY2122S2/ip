package duke.commands;

import duke.DukeList;
import duke.UI;

public class QuitCommand extends Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     */
    @Override
    public void execute(DukeList dukeList, UI ui) {
        ui.printMsg(ui.getGoodbyeMsg());
    }
}
