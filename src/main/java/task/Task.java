package task;

/**
 * Abstract class to represent a blueprint for a Task object
 * Not to be instantiated, serves as a blueprint to abstract common methods and attributes for subclasses
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks boolean isDone and returns a character to represent state
     * @return X if done, " "(whitespace) otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Used to help format the representation of the task to be saved to file
     * @return string that represents state of the task
     */
    public abstract String toStorageString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
