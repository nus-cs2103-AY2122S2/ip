public class Task {
    private static final char completedMark = 'X';
    private static final char incompleteMark = ' ';

    private String name;
    private Boolean done;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks the Task (marked as done)
     *
     * @return True if the state of Task was changed by marking (not done -> done)
     */
    public Boolean mark() {
        if (!this.isDone()) {
            this.done = true;
            return true;
        }

        return false;
    }

    /**
     * Unmarks the Task (marked as not done)
     *
     * @return True if the state of Task was changed by unmarking (done -> not done)
     */
    public Boolean unmark() {
        if (this.isDone()) {
            this.done = false;
            return true;
        }

        return false;
    }

    /**
     * Getter for the name of the Task
     *
     * @return Name of the Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the state (done) of the Task
     *
     * @return Task is done or not
     */
    public Boolean isDone() {
        return this.done;
    }

    public String nameWithStatus() {
        return String.format("[%c] %s",
                this.isDone() ? Task.completedMark : Task.incompleteMark,
                this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
