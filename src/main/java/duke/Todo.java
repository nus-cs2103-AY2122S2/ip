package duke;

public class Todo extends Task {
    private final String sym = "T";

    Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s", sym, super.getStatusIcon(), super.getDescription());
    }
}
