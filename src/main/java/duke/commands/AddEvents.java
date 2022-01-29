package duke.commands;

import duke.tasklist.DukeList;
import duke.task.Events;
import duke.ui.Ui;

public class AddEvents extends Command{

    private String msg;
    private String date;

    public AddEvents(String msg, String date){
        this.msg = msg;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, DukeList list) {
        Events ans = new Events(msg, date);
        list.add(ans);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
