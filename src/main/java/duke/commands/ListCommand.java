package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, DukeList list) {
        ui.printList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}