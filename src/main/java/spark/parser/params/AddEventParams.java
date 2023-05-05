package spark.parser.params;

import java.time.LocalDateTime;

public class AddEventParams extends AddTaskParams {
    protected LocalDateTime at;

    /**
     * Creates an object containing the necessary information
     * for the creation of a new event.
     *
     * @param title the title of the deadline.
     * @param at    the day of the event.
     */
    public AddEventParams(String title, LocalDateTime at) {
        super(title);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }
}
