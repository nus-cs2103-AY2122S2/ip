package duke.tasks;

/**
 * Encapsulates a task to be done.
 */
public class Todo extends Task {

    /**
     * Instantiates a new Todo task
     *
     * @param rank    the rank of the todo task
     * @param command description of the todo task
     */
    public Todo(int rank, String command) {
        super(rank, command);
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