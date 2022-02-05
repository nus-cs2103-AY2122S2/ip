package duke;

/**
 * Represents the Task class.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructor used to instantiate a Task class normally.
     *
     * @param description A String representing the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * An alternative constructor used to instantiate a Task class when loading from a pre-existing duke.txt file
     * at the start of a new duke session.
     *
     * @param description A String representing the description of the task.
     * @param mark An integer indicating if the task was done or not.
     */
    public Task(String description, int mark) {
        this.description = description;
        this.isDone = mark == 1;
    }

    /**
     * Returns the String representation of the Task class for users to read.
     *
     * @return A String representing the Task class for the user.
     */
    public String getTask() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns the description of the Task class.
     *
     * @return A String representing the description of the Task class.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the mark status of the Task class.
     *
     * @return A String representing the mark status of the Task class.
     */
    public String getMark() {
        if (this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * A method that, when called, updates the mark status of the Task class to be true.
     */
    public void isMarked() {
        this.isDone = true;
    }

    /**
     * A method that, when called, updates the mark status of the Task class to be false.
     */
    public void isUnmarked() {
        this.isDone = false;
    }

    /**
     * Overrides the default toString() method of the Task class.
     *
     * @return A String representing the description of the Task class.
     */
    @Override
    public String toString() {
        return description;
    }
}
