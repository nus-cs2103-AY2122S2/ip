package duke.task;

/**
 * Todo class.
 */
public class Todo extends Task {

    /**
     * Constructs todo item.
     * @param description Task description.
     * @param isMarked Indicates whether task is marked/done.
     */
    public Todo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    /**
     * Constructs todo item.
     * @param description Task description.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Returns string representation of a todo item.
     * @return Todo as a string
     */
    public String toString() {
        String status = super.isMarked() ? "X" : " ";
        return String.format("[T][%s] %s", status, super.getDescription());
    }

    /**
     * Returns string representation of a todo.
     * @return Todo as a string
     */
    public String toDataString() {
        return String.format("T,%s,%s",
                isMarked(),
                super.getDescription());
    }

}
