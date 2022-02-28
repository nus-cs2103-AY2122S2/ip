package duke.tasks;

/**
 * Represents a task to be done.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the task, including its type as [T].
     * @return Return the String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }

    /**
     * Returns the detail of the task, which contains its type and isDone status.
     * @return the detail of the task.
     */
    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        return "T" + " | " + status + " | " + this.description + "\n";
    }
}
