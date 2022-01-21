import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String title,LocalDateTime by) {
        super(title);
        this.by = by;
    }

    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.printDate(by) + ")";
    }
}
