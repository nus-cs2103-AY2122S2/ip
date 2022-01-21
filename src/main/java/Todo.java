/**
 * Todo class represents tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Class constructor specifying the task's description.
     */
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String formatForFile() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.description + "\n";
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}