package duke.data.task;

import duke.data.exception.IllegalValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task that has a deadline property.
 */
public class DeadlineTask extends Task {
    /**
     * The deadline of the task, in yyyy-mm-dd format
     */
    private final LocalDate deadline;

    /**
     * The deadline of the task parsed in MMM dd yy format.
     */
    private String deadlineString;

    /**
     * Constructs a new deadline task from the description, deadline,
     * boolean and id. Accepts date in the format of yyyy-mm-dd.
     *
     * @param description description of the task.
     * @param deadline deadline of the task.
     * @param done completion status of the task.
     * @param id id of the task.
     */
    public DeadlineTask(String description, String deadline, boolean done, String id, String tag) throws IllegalValueException {
        super(description, done, id, tag);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Constructs a new deadline task from the description and the
     * deadline. Accepts date in the format of yyyy-mm-dd.
     *
     * @param description description of the task.
     * @param deadline deadline of the task.
     */
    public DeadlineTask(String description, String deadline, String tagName) throws IllegalValueException {
        super(description, tagName);
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
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + this.deadlineString + ")" + " <" + super.getTagName() + ">";
    }
}
