package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class TodayTask extends Command {

    /**
     * Print out the tasks due or happening on current date to the console
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList list) {
        list.todayTask();
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
