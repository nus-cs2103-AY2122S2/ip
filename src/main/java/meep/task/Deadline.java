package meep.task;

import java.time.LocalDateTime;

import meep.parser.Parser;


/**
 * Represents the deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for class Deadline.
     *
     * @param title title of deadline task.
     * @param by    deadline of the task.
     */
    public Deadline(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    /**
     * Constructor for class Deadline.
     *
     * @param title title of deadline task.
     * @param done  status of the task.
     * @param by    deadline of the task.
     */
    public Deadline(String title, boolean done, LocalDateTime by) {
        super(title, done);
        this.by = by;
    }

    /**
     * Gets date.
     *
     * @return the date.
     */
    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[ D ]" + super.toString() + " (by: " + Parser.printDate(by) + ")";
    }
}
