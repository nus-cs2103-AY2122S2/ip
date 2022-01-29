package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class UnMarkCommand extends Command{

    private int index;

    public UnMarkCommand(int x){
        index = x;
    }

    /**
     * Unmark the specified task in the DukeList
     * @param ui Ui to communicate with user
     * @param tasks DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList tasks){
        tasks.unmark(index);
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