package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class TryAgain extends Command {
    private String err;
    public TryAgain(String e) {
        err = e;
    }

    @Override
    public String execute(Ui ui, DukeList list) {
        return err;
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
