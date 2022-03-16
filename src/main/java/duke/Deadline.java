package duke;

import java.time.LocalDateTime;

/**
 * set deadline on task
 */
public class Deadline extends Task {
    protected LocalDateTime due;

    /**
     * Constructor for Deadline
     *
     * @param d for task
     * @param due deadline
     */
    public Deadline(String d, LocalDateTime due) {
        super(d);
        this.due = due;
        this.type = "D";
    }

    /**
     * Constructor for deadline
     *
     * @param d for task
     * @param done check for done task
     * @param due deadline
     */
    public Deadline(String d, String done, LocalDateTime due) {
        super(d, done);
        this.due = due;
        this.type = "D";
    }

    /**
     * return deadline
     *
     * @return a string which represents the time
     */
    public String getDue() {
        return due.format(outDtf);
    }

    /**
     * Overriding ToString method
     * @return Deadline String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDue() + ")";
    }

}
