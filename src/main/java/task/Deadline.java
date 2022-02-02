package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, a type of Task that is due by a given time
 */
public class Deadline extends Task {
    protected LocalDateTime dueBy;

    public Deadline(String description, LocalDateTime dueBy) {
        super(description);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.dueBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toStorageString() {
        return String.format("D#%s#%s#%s", this.getStatusIcon(), this.description, this.dueBy.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
