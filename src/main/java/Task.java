public class Task {
    protected String description;
    protected Boolean isDone;
    public String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone == 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}