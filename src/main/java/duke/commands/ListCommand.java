package duke.commands;

import duke.DukeList;
import duke.UI;

public class ListCommand extends Command {
    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     */
    @Override
    public void execute(DukeList dukeList, UI ui) {
        ui.printMsg(dukeList.toString());
    }
}
