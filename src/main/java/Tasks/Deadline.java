package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a sub-class of Task that contains an additional LocalDateTime variable
 */
public class Deadline extends Task {

    public LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super("D", description);
        this.deadline = deadline;
    }

    /**
     * Generates a String representation of the Deadline object
     * @return String representation of the Deadline object
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    /**
     * Generates the String to be stored in the database
     * @return
     */
    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("D|%s|%s|%s", complete, this.getDescription(), this.deadline.toString());
    }

}
