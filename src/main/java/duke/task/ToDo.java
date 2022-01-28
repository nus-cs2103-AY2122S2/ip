package duke.task;

/**
 * Represents a task which is to be done.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo that is marked as not done by default.
     * 
     * @param task The description of the task.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Constructor to create a ToDo instance.
     * 
     * <p>This constructor accepts an additional isDone boolean to initialize 
     * a task that has been marked/unmarked as done.</p>
     * 
     * @param task The description of the task.
     * @param isDone Marks the task as done if true.
     */
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    /**
     * Formats a ToDo instance to be stored in an external file.
     */
    public String toFileFormat() {
        Integer i = this.isDone ? 1 : 0;
        return String.format("T | %d | %s\n", i, this.task);
    }

    /**
     * Returns the string representation of a ToDo.
     */
    @Override
    public String toString(){
        return String.format("[T]%s %s", this.statusString(), this.task);
    }
}