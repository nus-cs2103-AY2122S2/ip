package duke.task;

import java.time.LocalDate;

import duke.time.Time;

/**
 * Returns a type of task with a pending date, and may contain a time.
 */
public class Event extends Task {

    private LocalDate deadline;
    private String time;

    /**
     * Returns the task of type Event.
     *
     * @param isCompleted Status of the task.
     * @param task Task name.
     * @param deadline Date of the event.
     * @param time Time of the event. Optional field.
     */
    public Event(boolean isCompleted, String task, LocalDate deadline, String time) {
        super(task, isCompleted);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Returns the date of the event. Formats it in DD MM YYYY.
     *
     * @return A string to represent the date of the event.
     */
    public String getDeadline() {
        return Time.convertToString(this.deadline);
    }

    /**
     * Returns the time of the event.
     *
     * @return A string to represent the time of the event.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Returns the event task in a readable form.
     *
     * @return A string representing the details of the event task.
     */
    @Override
    public String toString() {
        if (this.time.equals("")) {
            return "[E]" + super.toString() + " (at: " + Time.convertToString(this.deadline) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + Time.convertToString(this.deadline) + ", " + this.time + ")";
        }
    }
}
