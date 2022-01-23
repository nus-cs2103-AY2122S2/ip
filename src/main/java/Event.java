import java.util.StringJoiner;

public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);

        if (at == null || at.isEmpty()) {
            throw new DukeException("The time of an Event cannot be empty.");
        }

        this.at = at;
    }

    public Event(String description, String at, boolean isDone) {
        this(description, at);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("E").add(String.valueOf(isDone ? 1 : 0)).add(description).add(at);
        return joiner.toString();
    }
}
