package athena.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    private final LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, OUTPUT_FORMATTER);
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + SAVE_SEPARATOR + dueDate.format(OUTPUT_FORMATTER);
    }

    @Override
    public String getIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", dueDate.format(OUTPUT_FORMATTER));
    }
}
