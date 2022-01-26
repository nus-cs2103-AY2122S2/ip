package duke.task;

import java.time.LocalDate;

import duke.time.Time;

/**
 * Returns a type of task with a pending date, and may contain a time.
 */
public class Deadline extends Task {

    private LocalDate deadline;
    private String time;

    /**
     * Returns the task of type Deadline.
     *
     * @param isCompleted Status of the task.
     * @param task Task name.
     * @param deadline Date of the deadline.
     * @param time Time of the deadline. Optional field.
     */
    public Deadline(boolean isCompleted, String task, LocalDate deadline, String time) {
        super(task, isCompleted);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Returns the date of the deadline. Formats it in DD MM YYYY.
     *
     * @return A string to represent the date of the deadline.
     */
    public String getDeadline() {
        return Time.convertToString(this.deadline);
    }

    /**
     * Returns the time of the deadline.
     *
     * @return A string to represent the time of the deadline.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Returns the deadline task in a readable form.
     *
     * @return A string representing the details of the deadline task.
     */
    @Override
    public String toString() {
        if (this.time.equals("")) {
            return "[D]" + super.toString() + " (by: " + Time.convertToString(this.deadline) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + Time.convertToString(this.deadline) + ", " + this.time + ")";
        }
    }
}
