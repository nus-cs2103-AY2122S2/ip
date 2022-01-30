package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A event task that has a deadline property.
 */
public class EventTask extends Task {
    /**
     * The deadline of the task.
     */
    private LocalDate deadline;

    /**
     * The deadline of the task parsed in MMM dd yy format.
     */
    private String deadlineString;

    /**
     * Constructs a new event task from the description and the
     * deadline. Accepts date in the format of yyyy-mm-dd.
     *
     * @param description description of the task.
     * @param deadline deadline of the task.
     * @param done completion status of the task.
     * @param id id of the task.
     */
    public EventTask(String description, String deadline, boolean done, String id) {
        super(description, done, id);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Constructs a new event task from the description and the
     * deadline. Accepts date int he form of yyyy-mm-dd.
     *
     * @param description
     * @param deadline
     */
    public EventTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the deadline property of the task.
     *
     * @return the deadline of the task.
     */
    public String getDeadline() {
        return this.deadlineString;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(by: " + this.deadlineString + ")";
    }
}
