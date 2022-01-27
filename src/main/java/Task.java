public class Task {
    protected String description;
    protected boolean isDone;
    protected Type eventType;

    public Task(String description, Type eventType) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Type getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "["
                + getStatusIcon()
                + "] "
                + description;
    }

    public void markAsComplete() {
        isDone = true;
    }

    public void markAsIncomplete() {
        isDone = false;
    }
}
