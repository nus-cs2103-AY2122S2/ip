package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand(){
    }

    /**
     * Prints closing message to console through Ui method
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public String execute(Ui ui, DukeList list) {
        return ui.showClosing();
    }

    /**
     * Returns true to end program
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
