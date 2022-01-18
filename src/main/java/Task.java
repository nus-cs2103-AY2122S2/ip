public class Task {
    protected boolean isDone;
    protected String description;

    Task(String d) {
        this.description = d;
        this.isDone = false;
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
