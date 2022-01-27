package duke;

/**
 * duke.Task object that stores event type, task name,
 * completion status and deadline (optional)
 *
 * @param description Name of task
 * @param isDone Completion status
 * @param eventType Name of task
 * @param time Name of task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Type eventType;

    public Task(String description, Type eventType) {
        this.description = description.trim();
        this.isDone = false;
        this.eventType = eventType;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Type getEventType() {
        return eventType;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "["
                + getStatusIcon()
                + "] "
                + description
                + " ";
    }

    public void markAsComplete() {
        isDone = true;
    }

    public void markAsIncomplete() {
        isDone = false;
    }
}
