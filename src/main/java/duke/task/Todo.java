package duke.task;

import java.io.DataInputStream;

/**
 * Represents a todo type task.
 * Contains only the description associated with the task.
 */
public class Todo extends Task {
    /**
     * Creates a todo type task with the supplied description.
     *
     * @param task Description of the task.
     */
    public Todo(String task) {
        super(TaskType.TODO, task);
    }

    /**
     * Creates an empty instance of the class to be populated by the
     * {@link #readSerializedData(DataInputStream)} method.
     * Usage should be avoided as there is potential for inconsistent states when the attributes are
     * not immediately populated after creation.
     */
    protected Todo() {
        this("");
    }
}
