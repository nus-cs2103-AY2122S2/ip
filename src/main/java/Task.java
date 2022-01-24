public class Task {
    private static final char completedMark = 'X';
    private static final char incompleteMark = ' ';

    private String name;
    private Boolean done;
    private char tag;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, Boolean done) {
        this.name = name;
        this.done = done;
        this.tag = ' ';
    }

    /**
     * Marks the Task (marked as done)
     *
     * @return true if the state of Task was changed by marking (not done -> done)
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
     * @return true if the state of Task was changed by unmarking (done -> not done)
     */
    public Boolean unmark() {
        if (this.isDone()) {
            this.done = false;
            return true;
        }

        return false;
    }

    /**
     * Getter for the tag of the Task
     *
     * @return tag of the Task
     */
    public char getTag() {
        return this.tag;
    }

    /**
     * Getter for the name of the Task
     *
     * @return name of the Task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the state (done) of the Task
     *
     * @return task is done or not
     */
    public Boolean isDone() {
        return this.done;
    }

    /**
     * Returns the tag, status, and name of the Task, formatted.
     *
     * @return formatted string of the Task info
     */
    public String nameWithStatus() {
        return String.format("[%c][%c] %s",
                this.getTag(),
                this.isDone() ? Task.completedMark : Task.incompleteMark,
                this.name);
    }

    /**
     * Returns Task info in a standard format for saving in file.
     *
     * @return formatted string for saving Task
     */
    public String fileSaveFormat() {
        return String.format("%c||%c||%s",
                this.getTag(),
                this.isDone() ? '1' : '0',
                this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
