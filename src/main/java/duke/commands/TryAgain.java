package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class TryAgain extends Command {

    @Override
    public void execute(Ui ui, DukeList list) {
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
