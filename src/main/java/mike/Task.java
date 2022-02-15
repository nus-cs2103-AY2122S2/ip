package mike;

abstract class Task {
    protected final String name;
    protected final boolean isDone;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task as in the name field.
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
     * @param searchWords Words to be searched for as specified by the user.
     * @return true if the task name contains the search words; else returns false.
     */
    public boolean containsSearchWords(String searchWords) {
        String taskName = this.name.toLowerCase();
        String search = searchWords.toLowerCase();
        return taskName.contains(search);
    }

    /**
     * Returns a String representing the name of the task.
     * @return String name of task for user.
     */
    @Override
    public String toString() {
        return String.format("name of task: %s", this.name);
    }
}
