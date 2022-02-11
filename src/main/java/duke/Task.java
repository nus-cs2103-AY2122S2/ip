package duke;

import java.util.ArrayList;

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
     * List of tags associated w task
     */
    protected ArrayList<String> tags;

    /**
     * Initialise task with description & eventType
     *
     * @param description Name of task
     * @param eventType Type of event
     */
    public Task(String description, Type eventType) {
        assert description != null && eventType != null;
        this.description = description.trim();
        this.isDone = false;
        this.eventType = eventType;
        this.tags = new ArrayList<>();
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
     * Return tags associated w task
     */
    public ArrayList<String> getTags() {
        return tags;
    }

    /**
     * Return string representation of the task
     * e.g. [E][X] Sample task
     *
     * @return String representation of task:
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + Ui.renderTags(getTags()) + " ";
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

    /**
     * Add tag to task
     */
    public void addTag(String tag) {
        tags.add(tag);
    }
}
