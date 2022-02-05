package duke.tasks;

import duke.time.ManagerDate;
import duke.time.ManagerTime;

/**
 * The TaskEvent class contains basic attributes
 * and behaviours of a Event Task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskEvent extends Task {
    /**
     * Date of Task.
     */
    private final String date;

    /**
     * Time of Task.
     */
    private final String time;

    /**
     * Sole constructor.
     *
     * @param name - name of task
     * @param date - date of task
     * @param time - time of task
     */
    public TaskEvent(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24();
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of TaskEvent.
     *
     * @return String - prefix of the TaskEvent
     */
    @Override
    public String getPrefix() {
        return "E";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of TaskEvent.
     *
     * @return String - prefix of the TaskEvent
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of TaskEvent.
     *
     * @return String - prefix of this TaskEvent
     */
    @Override
    public String getTime() {
        return this.time;
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String - String of this TaskEvent
     */
    @Override
    public String toString() {
        String prefix = "[E]";
        return prefix + super.toString() + " on: "
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
