import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    String formatDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public Task mark() {
        return new Deadline(this.description, this.by, true);
    }
    
    @Override
    public Task unmark() {
        return new Deadline(this.description, this.by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }
}
