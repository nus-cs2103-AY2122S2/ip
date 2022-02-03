package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;
import Duke.Tasks.Task;
import Duke.Tasks.TaskEvent;
import Duke.Time.ManagerDate;
import Duke.Time.ManagerTime;

/**
 * The CommandEvent class contains basic attributes and
 * behaviours of a Event Command.
 *
 * @author  Melvin Chan Zijun
 */
public class CommandEvent extends Command {
    /**
     * Name of Task to be added
     */
    private final String name;

    /**
     * Date of Task to be added
     */
    private final String date;

    /**
     * Time of Task to be added
     */
    private final String time;

    /**
     * Sole constructor.
     *
     * @param name - name of Event Task to be created
     * @param date - date of Event Task to be created
     * @param time - time of Event Task to be created
     */
    public CommandEvent(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * Overrides method in parent class.
     * This method models the execution of a Event Command.
     * A Event Task gets created, the TaskList adds the task,
     * the Storage saves it and the Ui lets the user know
     * that the task was executed successfully.
     *
     * @param tasks - for TaskList to add Event Task
     * @param ui - to let user know that execution was successful
     * @param storage - to save updated TaskList
     * @throws DukeException - thrown if error saving data or
     *                         invalid date and/or time
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ManagerDate md = new ManagerDate(date);
        ManagerTime mt = new ManagerTime(time);

        if (md.isDateValid() && mt.isTimeValid()) {
            Task task = new TaskEvent(this.name, this.date, this.time);
            tasks.add(task);
            storage.save(tasks);
            ui.showTaskAdded();
        } else {
            throw new DukeException("Invalid date and/or time!");
        }
    }
}
