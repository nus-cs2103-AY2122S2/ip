package bernie.tasks;

import bernie.exceptions.InvalidArgumentException;

/**
 * Task is contained in the TaskList
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with description and initialises as an undone task
     * @param description String
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
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

    /**
     * Checks whether the task can be marked
     * @throws InvalidArgumentException if the user attempts to mark a marked task
     */
    public void checkMark() throws InvalidArgumentException {
        if (isDone) {
            throw new InvalidArgumentException("Cannot mark a task already done!");
        }
    }

    /**
     * Checks whether the task can be unmarked
     * @throws InvalidArgumentException if the user attempts to unmark a unmarked task
     */
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
