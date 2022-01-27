package bernie.tasks;

public class ToDo extends Task {
    /**
     * Constructs a ToDo class for a general Task
     * @param description String
     */
    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
