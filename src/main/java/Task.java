public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isTodo;
    protected boolean isDeadline;
    protected boolean isEvent;
    protected String time;

    public Task(String description, boolean isTodo, boolean isDeadline, boolean isEvent, String time) {
        this.description = description;
        this.isDone = false;
        this.isTodo = isTodo;
        this.isDeadline = isDeadline;
        this.isEvent = isEvent;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskTypeIcon() {
        return (isTodo ? "T" : isDeadline ? "D" : isEvent ? "E" : " ");
    }
}
