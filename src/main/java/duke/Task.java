package duke;

/**
 * Base class for task object that stores event type,
 * task name, completion status
 */
public class Task {

    /**
     * Name of task
     */
    protected String description;

    /**
     * Completion status of task
     */
    protected boolean isDone;

    /**
     * Event type of task
     */
    protected Type eventType;

    /**
     * Initialise task with description & eventType
     *
     * @param description Name of task
     * @param eventType Type of event
     */
    public Task(String description, Type eventType) {
        this.description = description.trim();
        this.isDone = false;
        this.eventType = eventType;
    }

    /**
     * Return name/description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return completion status of task as either "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return eventType of task
     */
    public Type getEventType() {
        return eventType;
    }

    /**
     * Return completion status of task
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Return string representation of the task
     * e.g. [E][X] Sample task
     *
     * @return String representation of task:
     */
    @Override
    public String toString() {
        return "["
                + getStatusIcon()
                + "] "
                + description
                + " ";
    }

    /**
     * Mark task as completed
     */
    public void markAsComplete() {
        isDone = true;
    }

    /**
     * Name task as incomplete
     */
    public void markAsIncomplete() {
        isDone = false;
    }
}
