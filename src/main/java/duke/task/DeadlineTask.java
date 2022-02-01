package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tasks that are set with a deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDate taskDate;
    protected LocalTime taskTime;

    /**
     * Constructor for deadline task with date and time using varargs.
     * Depending on the length of the taskDateTime, determine whether to accommodate
     *
     * @param title Title of task
     * @param taskDateTime Date of task deadline
     */
    public DeadlineTask(String title, String... taskDateTime) {
        super(title);
        this.type = TaskType.DEADLINE;
        if (taskDateTime.length >= 1) {
            this.taskDate = LocalDate.parse(taskDateTime[0]);
        }
        if (taskDateTime.length == 2) {
            this.taskTime = LocalTime.parse(taskDateTime[1]);
        }
    }

    /**
     * Constructor for deadline task, specified done state and date.
     *
     * @param title Title of task
     * @param isDone Done state deadline
     * @param taskDate Date of Task deadline
     */
    public DeadlineTask(String title, Boolean isDone, String taskDate) {
        super(title, isDone);
        this.type = TaskType.DEADLINE;
        this.taskDate = LocalDate.parse(taskDate);
        this.taskTime = null;
    }

    /**
     * Constructor for deadline task, specified done state, date and time.
     *
     * @param title Title of task
     * @param isDone Done state
     * @param taskDate Date of task deadline
     * @param taskTime Time of task deadline
     */
    public DeadlineTask(String title, Boolean isDone, String taskDate, String taskTime) {
        super(title, isDone);
        this.type = TaskType.DEADLINE;
        this.taskDate = LocalDate.parse(taskDate);
        this.taskTime = LocalTime.parse(taskTime);
    }

    /**
     * Concatenate deadline printing string.
     *
     * @return String for printing the deadline
     */
    public String getByTime() {
        return "(by: " + taskDate + (taskTime != null ? " " + taskTime.toString() : "") + ")";
    }

    /**
     * Concatenate task into format to save to file.
     *
     * @return String for file saving
     */
    public String toOutputLine() {
        return this.getType() + " | " + (isDone ? "1 " : "0 ") + "| " + this.title + " | "
                + taskDate.toString() + (taskTime != null ? " | " + taskTime.toString() : "");
    }

    /**
     * Combines the title with the deadline.
     * Used when printing the task with the List command.
     *
     * @return String with title and deadline of the task
     */
    @Override
    public String toString() {
        return this.title + " " + getByTime();
    }
}
