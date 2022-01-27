package bernie.tasks;
/**
 * ToDo is one of the Task that can be created by the user input
 */
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
