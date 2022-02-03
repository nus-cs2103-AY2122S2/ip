package Duke.Tasks;

import Duke.Time.ManagerDate;
import Duke.Time.ManagerTime;

/**
 * The TaskDeadline class contains basic attributes
 * and behaviours of a Deadline Task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class TaskDeadline extends Task {
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
    public TaskDeadline(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24();
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of TaskDeadline.
     *
     * @return String - prefix of the TaskDeadline
     */
    @Override
    public String getPrefix() {
        return "D";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of TaskDeadline.
     *
     * @return String - prefix of the TaskDeadline
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of TaskDeadline.
     *
     * @return String - prefix of this TaskDeadline
     */
    @Override
    public String getTime() {
        return this.time;
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String - String of this TaskDeadline
     */
    @Override
    public String toString() {
        String prefix = "[D]";
        return prefix + super.toString() + " on: "
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
