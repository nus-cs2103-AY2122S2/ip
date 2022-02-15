package duke.tasks;

import duke.time.ManagerDate;
import duke.time.ManagerTime;

/**
 * The DeadlineTask class contains basic attributes
 * and behaviours of a deadline task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class DeadlineTask extends Task {
    /**
     * date of Task.
     */
    private final String date;

    /**
     * time of Task.
     */
    private final String time;

    /**
     * Sole constructor.
     *
     * @param name name of task
     * @param date date of task
     * @param time time of task
     */
    public DeadlineTask(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24hr();
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of deadline task.
     *
     * @return String prefix of deadline task
     */
    @Override
    public String getPrefix() {
        return "D";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of deadline task.
     *
     * @return String date of deadline task
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of deadline task.
     *
     * @return String time of this deadline task
     */
    @Override
    public String getTime() {
        return this.time;
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String deadline task in String form
     */
    @Override
    public String toString() {
        String prefix = "[D]";
        return prefix + super.toString() + " on: "
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
