package duke.tasks;

import duke.time.ManagerDate;
import duke.time.ManagerTime;

/**
 * The EventTask class contains basic attributes
 * and behaviours of an event task. It extends
 * from the Task class.
 *
 * @author  Melvin Chan Zijun
 */
public class EventTask extends Task {
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
    public EventTask(String name, String date, String time) {
        super(name);
        this.date = date;
        this.time = new ManagerTime(time).getFormat24hr();
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the prefix of event task.
     *
     * @return String prefix of the TaskEvent
     */
    @Override
    public String getPrefix() {
        return "E";
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the date of event task.
     *
     * @return String prefix of the TaskEvent
     */
    @Override
    public String getDate() {
        return this.date;
    }

    /**
     * Overrides the abstract method of its parent class.
     * Returns the time of event task.
     *
     * @return String prefix of event task
     */
    @Override
    public String getTime() {
        return this.time;
    }

    /**
     * Overrides the toString() method of the parent.
     *
     * @return String event task in String form
     */
    @Override
    public String toString() {
        String prefix = "[E]";
        return prefix + super.toString() + " on: "
                + ManagerDate.formatDate(this.date) + " "
                + this.time;
    }
}
