package puke.task;

public abstract class Task {
    protected final String name;
    protected int status; // 0: undone, 1: done

    Task(String taskName) {
        this.name = taskName;
        this.status = 0;
    }

    public boolean isDone() {
        return status == 1;
    }

    public void mark() {
        this.status = 1;
    }

    public void unmark() {
        this.status = 0;
    }

    /**
     * Check if the task name contains a specified string.
     *
     * @param s String to match in the task name.
     * @return True if the task name contains the string; False otherwise.
     */
    public boolean containKeyword(String s) {
        return this.name.contains(s);
    }

    abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") // get the icon according to the status
                + "] " + this.name;
    }
}