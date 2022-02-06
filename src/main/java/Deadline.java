import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
