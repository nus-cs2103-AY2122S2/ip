package duke.task;

import duke.common.Constant;

import java.time.LocalDateTime;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates an instance of deadline.
     *
     * @param description of deadline.
     * @param time        due of the deadline.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, Task.Type.DEADLINE);
        this.by = time;
        assert this.by != null : "LocalDateTime should be assigned";
    }

    /**
     * Gets the time due of deadline in a specific format.
     *
     * @return time due of deadline.
     */
    public String getBy() {
        return this.by.format(Constant.OUT_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by.format(Constant.OUT_TIME_FORMATTER) + ")";
    }
}
