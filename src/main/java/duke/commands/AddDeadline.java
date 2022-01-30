package duke.commands;

import duke.tasklist.DukeList;
import duke.task.Deadlines;
import duke.ui.Ui;

public class AddDeadline extends Command{

    private String msg;
    private String date;

    /**
     * Creates a AddDeadline Command
     * @param msg Task description of the Deadline
     * @param date Date that the task is due
     */
    public AddDeadline(String msg, String date){
        this.msg = msg;
        this.date = date;
    }

    /**
     * Creates a new Deadline task and adds it to the list
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList list) {
        Deadlines ans = new Deadlines(msg, date);
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
