package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class MarkCommand extends Command{

    private int index;

    public MarkCommand(int x){
        index = x;
    }

    /**
     * Mark the specified task in the DukeList
     * @param ui Ui to communicate with user
     * @param tasks DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList tasks){
        tasks.mark(index);
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
