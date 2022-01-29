package duke.commands;

import duke.tasklist.DukeList;
import duke.task.Deadlines;
import duke.ui.Ui;

public class AddDeadline extends Command {

    private String msg;
    private String date;

    public AddDeadline(String msg, String date) {
        this.msg = msg;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, DukeList list) {
        Deadlines ans = new Deadlines(msg, date);
        list.add(ans);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
