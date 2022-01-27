package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    private final String type = "D";
    private final LocalDateTime dateTime;

    /**
     * Constructs a deadline object.
     *
     * @param description String name of the task.
     * @param dateAndTime String input of the date and time of the deadline,
     *                    in ISO format.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by: "
                + super.localDateTimeToString(this.dateTime) + ")";
    }
}
