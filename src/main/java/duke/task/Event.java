package duke.task;

import java.time.LocalDate;

import duke.time.Time;

/**
 * A type of task with a pending date, and may contain a time.
 */
public class Event extends Task {

    private LocalDate deadline;
    private String time;

    /**
     * Initializes the task of type Event.
     *
     * @param completed Status of the task.
     * @param task Task name.
     * @param deadline Date of the event.
     * @param time Time of the event. Optional field.
     */
    public Event(boolean completed, String task, LocalDate deadline, String time) {
        super(task, completed);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Gets the date of the event. Formats it in DD MM YYYY.
     *
     * @return A string to represent the date of the event.
     */
    public String getDeadline() {
        return Time.convertToString(this.deadline);
    }

    /**
     * Gets the time of the event.
     *
     * @return A string to represent the time of the event.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Writes the event task in a readable form.
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
