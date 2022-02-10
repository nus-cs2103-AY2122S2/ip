package paggro.task;

import java.util.ArrayList;

/**
 * This class encapsulates a Task object representing a task as described by the user.
 */
public abstract class Task {
    /** The description of the Task object. */
    private String description;
    /** The boolean indicating if the task is complete. */
    private boolean isDone;
    /** The list of tags associated with the Task object */
    private ArrayList<Tag> tags;

    /**
     * Default constructor of Task.
     *
     * @param des String description of Task object.
     */
    public Task(String des) {
        description = des;
        isDone = false;
        tags = new ArrayList<>();
    }

    /**
     * Constructor of Task specifying if it is complete.
     *
     * @param des String description of Task object.
     * @param isDone A boolean indicating if the task is complete.
     */
    public Task(String des, boolean isDone) {
        description = des;
        this.isDone = isDone;
        tags = new ArrayList<>();
    }

    /**
     * Returns the description of the Task object.
     *
     * @return String description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the Task object is done.
     *
     * @return True if the task is done and false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Adds a tag to the list of tags
     *
     * @param tag Tag to be added to task.
     */
    public void tagTask(Tag tag) {
        tags.add(tag);
    }

    public ArrayList<Tag> getTags() {
        return this.tags;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    /**
     * Parses the task into a string formatted to be saved to storage.
     *
     * @return String to be saved to storage.
     */
    public abstract String parseTask();
}
