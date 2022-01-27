import java.time.LocalDate;

public class Deadline extends Task {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.description + " (by: " + Date.toString(this.date) + ")";
    }
}