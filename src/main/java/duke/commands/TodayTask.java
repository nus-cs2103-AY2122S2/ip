package duke.commands;

import duke.tasklist.DukeList;
import duke.ui.Ui;

public class TodayTask extends Command{

    @Override
    public void execute(Ui ui, DukeList list) {
        list.todayTask();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
