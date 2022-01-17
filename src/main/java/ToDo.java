/**
 * Represent the Todo things of task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of ToDo.
     *
     * @return [T] with the status and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
