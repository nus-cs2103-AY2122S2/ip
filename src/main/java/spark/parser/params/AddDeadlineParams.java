package spark.parser.params;

import java.time.LocalDateTime;

public class AddDeadlineParams extends AddTaskParams {
    protected LocalDateTime by;

    /**
     * Creates an object containing the necessary information
     * for the creation of a new deadline.
     *
     * @param title the title of the deadline.
     * @param by    the last day of the deadline.
     */
    public AddDeadlineParams(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }
}
