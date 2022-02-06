package duke.commands;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;
import duke.tasklist.DukeList;
import duke.ui.Ui;

public class AddTask extends Command {

    private String cmd;
    private String date;
    private String msg;

    /**
     * Creates an AddTask command with date
     * @param cmd task to create
     * @param msg msg of task
     * @param date date of task
     */
    public AddTask(String cmd, String msg, String date) {
        this.cmd = cmd;
        this.msg = msg;
        this.date = date;
    }

    /**
     * Creates an AddTask command without date
     * @param cmd Task to create
     * @param msg Body of the task
     */
    public AddTask(String cmd, String msg) {
        this.cmd = cmd;
        this.msg = msg;
    }

    /**
     * Creates the task based on cmd, then adds it to the DukeList
     * @param ui Ui to communicate with user
     * @param list List of tasks
     */
    @Override
    public String execute(Ui ui, DukeList list) {
        Task t;
        switch(cmd) {
        case "todo" :
            t = new ToDos(msg);
            break;
        case "deadline" :
            t = new Deadlines(msg, date);
            break;
        default :
            t = new Events(msg, date);
        }
        list.add(t);
        return ui.addTask(t, list.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
