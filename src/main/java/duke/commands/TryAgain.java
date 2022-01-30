package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class TryAgain extends Command {

    @Override
    public void execute(Ui ui, DukeList list) {
    }

    /**
     * Returns false so program does not quit
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
