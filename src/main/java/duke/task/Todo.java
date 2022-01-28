package duke.task;

/**
 * Represents the to-do Duke.task entered by user
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
