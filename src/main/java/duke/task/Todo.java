package duke.task;

/**
 * A type of task called todo.
 */
public class Todo extends Task {

    public Todo (boolean completed, String task) {
        super(task, completed);
    }

    /**
     * Writes the todo task in a readable form.
     *
     * @return A string representing the details of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}