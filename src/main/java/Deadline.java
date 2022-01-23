import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);

        if (by == null) {
            throw new DukeException("The deadline of a Deadline must be specified");
        }

        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("D").add(String.valueOf(isDone ? 1 : 0)).add(description).add(by.toString());
        return joiner.toString();
    }
}
