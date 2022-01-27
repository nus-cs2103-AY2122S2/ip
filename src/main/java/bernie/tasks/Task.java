package bernie.tasks;

import bernie.exceptions.InvalidArgumentException;

/**
 * Task is contained in the TaskList: it contains description, done status and id.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with description, id and isDone
     * @param description String
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public void checkMark() throws InvalidArgumentException {
        if (isDone) {
            throw new InvalidArgumentException("Cannot mark a task already done!");
        }
    }

    public void checkUnmark() throws InvalidArgumentException {
        if (!isDone) {
            throw new InvalidArgumentException("Cannot unmark a task not done!");
        }
    }

    @Override
    public String toString() {
        return isDone ? String.format("[X] %s", description) : String.format("[ ] %s", description);
    }
}
