package mike;

/**
 * Represents a task.
 */
abstract class Task {
    protected final String name;
    protected final boolean isDone;

    /**
     * Constructor for task.
     *
     * @param name Task name.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructor for task.
     *
     * @param name Task name.
     * @param isDone Whether task is to be marked as done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of task.
     */
    public String getTaskName() {
        return this.name;
    }

    abstract Task markAsDone();

    abstract Task markAsUndone();

    abstract String convertToStoredTaskFormat();

    /**
     * Returns true if the task name contains the specified search words.
     *
     * @param searchWords Words to be searched for in the task name.
     * @return Returns true if the task name contains the search words; else returns false.
     */
    public boolean containsSearchWords(String searchWords) {
        String taskName = this.name.toLowerCase();
        String search = searchWords.toLowerCase();
        return taskName.contains(search);
    }

    /**
     * Returns a String representing the task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return String.format("name of task: %s", this.name);
    }
}
