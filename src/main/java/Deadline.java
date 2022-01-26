import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * The Deadline class extends the Task class by adding a due time.
 *
 * @author Rdac0
 */
public class Deadline extends Task{
    private LocalDate time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Creates a Deadline object.
     *
     * @param name The name of the Deadline.
     * @param time The due time of the Deadline.
     */
    public Deadline(String name, String time) throws DateTimeParseException {
        super(name);
        this.time = LocalDate.parse(time);
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[D]" + mark + getName() +
                " (by: " + this.time.format(formatter) + ")";
    }
}
