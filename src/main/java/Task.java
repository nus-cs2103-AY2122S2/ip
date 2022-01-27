abstract class Task {
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

    abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") // get the icon according to the status
                + "] " + this.name;
    }
}