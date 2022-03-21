package tasks;

/** A class that functions as an abstraction of a todo task. */
public class Todo extends Task {

    private final Task.TaskType taskType = Task.TaskType.TODO;
    private final String taskName;

    /**
     * Constructor method for the Todo task.
     *
     * @param taskName Name of the Event task.
     */
    public Todo(String taskName) {
        this.taskName = taskName;
    }

    /**
     * The string representation of the task to be displayed to the user.
     *
     * @return A string representation of the task for the user.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone() ? "X" : " ", this.taskName);
    }


    /**
     * Returns the description of the task.
     * @return The description of the todo.
     */
    public String getDescription() {
        return this.taskName;
    }

    /**
     * Converts the task into a string representation to be used to be saved on disk.
     *
     * @return The task in string format to be used to be saved on disk.
     */
    public String exportToString() {
        return String.format("%s %s %s", this.taskType, this.taskName, this.isDone());
    }
}
