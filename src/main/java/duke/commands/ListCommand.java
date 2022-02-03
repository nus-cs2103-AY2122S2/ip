package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Prints the list of tasks to the console
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList list) {
        ui.printList(list);
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
