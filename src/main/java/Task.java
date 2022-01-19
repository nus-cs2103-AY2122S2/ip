public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getT() {
        return description;
    }

    @Override
    public String toString() {
        String v = "[" + this.getStatusIcon() + "]" + " " + this.getT();
        return v;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }
}
