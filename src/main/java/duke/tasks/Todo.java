package duke.tasks;

/**
 * Encapsulates a task to be done.
 */
public class Todo extends Task {

    /**
     * Instantiates a new Todo task
     *
     * @param taskDescription description of the todo task
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * String representation of the Todo task
     *
     * @return the string representation of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
