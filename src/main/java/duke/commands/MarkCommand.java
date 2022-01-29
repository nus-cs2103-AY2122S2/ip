package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class MarkCommand extends Command{

    private int index;

    public MarkCommand(int x){
        index = x;
    }

    @Override
    public void execute(Ui ui, DukeList tasks){
        tasks.mark(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
