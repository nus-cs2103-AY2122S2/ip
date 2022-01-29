package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class UnMarkCommand extends Command {

    private int index;

    public UnMarkCommand(int x) {
        index = x;
    }

    @Override
    public void execute(Ui ui, DukeList tasks) {
        tasks.unmark(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}