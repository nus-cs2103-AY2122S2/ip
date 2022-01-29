package duke.commands;

import duke.tasklist.DukeList;
import duke.task.Events;
import duke.ui.Ui;

public class AddEvents extends Command{

    private String msg;
    private String date;

    /**
     * Creates a AddEvents Command
     * @param msg Task description of the Event
     * @param date Date that the task is happening
     */
    public AddEvents(String msg, String date){
        this.msg = msg;
        this.date = date;
    }

    /**
     * Creates a new Event task and adds it to the list
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList list) {
        Events ans = new Events(msg, date);
        list.add(ans);
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
