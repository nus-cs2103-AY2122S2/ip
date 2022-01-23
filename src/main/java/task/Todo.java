package task;

public class Todo extends Task {

    /**
     * Constructor for Todo Task.
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Another constructor for Todo Task.
     * @param description
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Get the saved message corresponding to this task.
     * @return save message for the task.
     */
    @Override
    public String getSaveMessage() {
        if (this.isDone) {
            return String.format("T / 1 / %s", this.description);
        } else {
            return String.format("T / 0 / %s", this.description);
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[T][X] %s", this.description);
        } else {
            return String.format("[T][ ] %s", this.description);
        }
    }
}