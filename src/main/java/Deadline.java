import java.util.StringJoiner;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);

        if (by == null || by.isEmpty()) {
            throw new DukeException("The deadline of a Deadline cannot be empty.");
        }

        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("D").add(String.valueOf(isDone ? 1 : 0)).add(description).add(by);
        return joiner.toString();
    }
}
