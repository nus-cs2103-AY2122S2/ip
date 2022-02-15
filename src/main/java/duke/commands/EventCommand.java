package duke.commands;

import duke.exceptions.DukeException;
import duke.system.Storage;
import duke.system.TaskList;
import duke.system.Ui;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.time.ManagerDate;
import duke.time.ManagerTime;

/**
 * The EventCommand class contains basic attributes and
 * behaviours of a event command.
 *
 * @author  Melvin Chan Zijun
 */
public class EventCommand extends Command {
    /**
     * Name of task
     */
    private final String name;

    /**
     * Date of task
     */
    private final String date;

    /**
     * Time of task
     */
    private final String time;

    /**
     * Validity of task (based on date and time check)
     */
    private final boolean isTaskValid;

    /**
     * Sole constructor.
     *
     * @param name name of task
     * @param date date of task
     * @param time time of task
     */
    public EventCommand(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;

        ManagerDate md = new ManagerDate(date);
        ManagerTime mt = new ManagerTime(time);

        this.isTaskValid = md.isDateValid() && mt.isTimeValid();
    }

    /**
     * Overrides method in parent class.
     * Executes the event command, saves the data and returns
     * a message to let user know that execution was
     * successful.
     * Task will only be created if date and time is valid.
     *
     * @param tasks duke's task list
     * @param ui duke's ui
     * @param storage duke's storage
     * @return String message of either successful or
     *                unsuccessful execution
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!this.isTaskValid) {
                throw new DukeException("Invalid date and/or time!");
            } else {
                Task task = new EventTask(this.name, this.date, this.time);
                tasks.add(task);
                storage.save(tasks);
                return ui.showTaskAdded();
            }
        } catch (DukeException e) {
            return ui.showException(e);
        }
    }
}
