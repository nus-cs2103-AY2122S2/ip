package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Trigger is a Task to trigger a message.
 */
public class Trigger extends Task {
    private static String type = "TRG";

    /**
     * Constructor for Trigger task takes in the description of the task and whether it
     * has been marked as done.
     * @param description the description of the Trigger task
     * @param isDone      true if the task has been marked as done
     */
    public Trigger(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for Trigger task takes in the description of the task.
     * @param description the description of the Trigger task
     */
    public Trigger(String description) {
        this(description, false);
    }

    /**
     * Returns the String representation of todo task.
     * @return string representation of todo task
     */
    @Override
    public String toString() {
        assert this.description != null;
        return this.description;
    }

    @Override
    public void updateDate(String newDate) throws DukeException {
        System.out.println("No date value for Todo task");
    }

    @Override
    public void updateTime(String newTime) throws DukeException {
        System.out.println("No time value for Todo task");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void updateDescription(String description) {
        this.description = description;
    }
}
