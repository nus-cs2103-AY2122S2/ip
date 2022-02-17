package ann.data.tasks;

/**
 * Represents a basic task.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Task {
    private static final TaskType TASK_TYPE = TaskType.TODO;
    public static final String KEYWORD = TASK_TYPE.getKeyword();
    protected String content;
    protected boolean isDone;

    /**
     * Creates a new Task from the specified content.
     *
     * @param content a String which describes what needs to be done.
     */
    public Task(String content) {
        assert !content.isBlank() : "The Task should have a non-empty description";
        this.content = content;
        this.isDone = false;
    }

    /**
     * Creates a new Task from the specified content and completion status.
     *
     * @param content a String which describes what needs to be isDone.
     * @param isDone a boolean which indicates whether the task is isDone.
     */
    public Task(String content, boolean isDone) {
        assert !content.isBlank() : "The Task should have a non-empty description";
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the Task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the Task to be displayed to users.
     *
     * @return the String representation of the Task to be displayed to users.
     */
    @Override
    public String toString() {
        return "[T]" + (this.isDone ? "[X] " : "[ ] ") + this.content;
    }

    /**
     * Returns the String representation of the Task to be written to a file.
     *
     * @return the String representation of the Task to be written to a file.
     */
    public String toFileString() {
        return "T, " + (this.isDone ? "1, " : "0, ") + this.content;
    }
}
