package duke.task;

/**
 * Returns a type of task called todo.
 */
public class Todo extends Task {

    /**
     * Returns the task of type Todo.
     *
     * @param isCompleted Status of the task.
     * @param task Task name.
     */
    public Todo (boolean isCompleted, String task) {
        super(task, isCompleted);
    }

    /**
     * Returns the todo task in a readable form.
     *
     * @return A string representing the details of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
