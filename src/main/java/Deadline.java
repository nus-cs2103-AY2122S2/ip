import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulate information of task with deadline.
 */
public class Deadline extends Task{

    /**
     * Normal constructor.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[D]" + super.toString() + " (by: " + dtf.format(super.dateTime) + ")";
    }
}
