package duke.task;

/**
 * Represents a Todo task created by the user that are/will be stored in the database.
 * A <code>Todo</code> inherits from <code>Tasks</code> and is represented by its
 * name, and completion status e.g.,<code>"Eat dinner", true</code>
 */
public class Todos extends Tasks {

    // Constructor of Todos
    public Todos(String taskName) {
        super(taskName);
    }

    public Todos(String taskName, boolean completion) {
        super(taskName, completion);
    }

    /**
     * Returns a new completed instance of the task.
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Todos completeTask() {
        return new Todos(name, true);
    }

    /**
     * Returns a new uncompleted instance of the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Todos uncompleteTask() {
        return new Todos(name, false);
    }

    // Save to database format
    public String toDatabaseString() {
        return "T | " + (super.getCompletion() == true ? "X" : " ") + " | " + super.getName() + "\n";
    }

    // toString returning Todos
    public String toString() {
        return "[T][" + (this.getCompletion() == true ? "X" : " ") + "] " + super.getName();
    }
}
