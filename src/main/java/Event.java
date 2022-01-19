public class Event extends Task {
    private final String at;

    public Event(String description, String at) {
        super(description);

        if (at == null || at.isEmpty()) {
            throw new DukeException("The time of an Event cannot be empty.");
        }

        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
