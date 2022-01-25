public abstract class Task {
    protected boolean isDone;
    protected String description;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[" + tempStr + "] " + this.description;
    }
}
