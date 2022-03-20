package duke;

import java.time.LocalDateTime;

/**
 * The type Deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param desc the description
     * @param by   the deadline
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc, "D");
        this.by = by;
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param desc the description
     * @param by   the deadline
     * @param done the done
     */
    public Deadline(String desc, LocalDateTime by, boolean done) {
        super(desc, done, "D");
        this.by = by;
    }
    @Override
    public LocalDateTime getBy() {
        return this.by;
    }
    @Override
    public Task mark() {
        return new Deadline(this.desc, this.by, true);
    }
    @Override
    public Task unmark() {
        return new Deadline(this.desc, this.by, false);
    }
}
