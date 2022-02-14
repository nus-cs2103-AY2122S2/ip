package duke.data.task;

import java.util.UUID;

import duke.data.exception.IllegalValueException;
import duke.data.tag.Tag;

/**
 * A task class.
 */
public class Task {
    /**
     * The description of a task.
     */
    protected String description;

    /**
     * Completion status of a task.
     */
    protected boolean done;

    /**
     * id of the task.
     */
    protected String id;

    /**
     * Tag of the task.
     */
    protected Tag tag;

    /**
     * Constructs a task from given description, id and completion status.
     *
     * @param description description of the task.
     * @param done completion status of the task.
     * @param id id of the task.
     */
    public Task(String description, boolean done, String id, String tagName) throws IllegalValueException {
        this.description = description;
        this.done = done;
        this.id = id;
        if (tagName != "") {
            this.tag = new Tag(tagName);
        }
    }

    /**
     * Constructs a new task from given description.
     *
     * @param description description of a task.
     */
    public Task(String description, String tagName) throws IllegalValueException {
        this.description = description;
        this.done = false;
        this.id = UUID.randomUUID().toString();
        if (tagName != "") {
            this.tag = new Tag(tagName);
        }
    }

    public String getId() {
        return this.id;
    }

    /**
     * Returns the description property of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the current task as completed.
     */
    public void markDone() {
        this.done = true;
        assert this.done;
    }

    /**
     * Marks the current task as undone.
     */
    public void markUndone() {
        this.done = false;
        assert !this.done;
    }

    public String getTagName() {
        if (this.tag == null) {
            return "";
        }
        return this.tag.toString();
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        return status + " " + this.description;
    }
}
