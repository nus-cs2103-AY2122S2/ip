package tsohg;

/**
 * The abstract class representing tasks.
 */
public abstract class Task {

    protected final String name;
    protected boolean isDone;
    protected boolean isHighPriority;

    /**
     * Constructor of the class.
     * @param name The name of the task.
     */
    Task(String name, boolean isHighPriority) {
        this.name = name;
        this.isHighPriority = isHighPriority;
        this.isDone = false;
    }

    /**
     * Check if the name of the task contains the keyword.
     * @param keyword The keyword.
     * @return True if the name contains the keyword else false.
     */
    public boolean contains(String keyword) {
        return name.contains(keyword);
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Switch the priority of the item.
     */
    public void switchPriority() {
        this.isHighPriority = !this.isHighPriority;
    }

    /**
     * Returns the status icon of a task.
     * @return the status icon of a task.
     */
    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected String getPriority() {
        return isHighPriority ? "H" : "L";
    }

    /**
     * Returns the stored data representation.
     * @return The stored data representation.
     */
    public abstract String toStore();

}
