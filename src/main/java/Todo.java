/**
 * Represent tasks without any date/time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Todo) and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
