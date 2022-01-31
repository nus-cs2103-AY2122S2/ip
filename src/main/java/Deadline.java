import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dueBy;

    public Deadline(String description, LocalDateTime dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), dueBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
