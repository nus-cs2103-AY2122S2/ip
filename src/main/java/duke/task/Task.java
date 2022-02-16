package duke.task;

import duke.DukeException;
import duke.common.Messages;

/**
 * A class that represents a task.
 */
public class Task {
    private final String title;
    private boolean isDone;

    /**
     * Creates a Task instance with a title.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves and parses the save format for task.
     *
     * @return The save format for event
     * @throws DukeException If the class return an invalid class type,
     * it will throw a DukeException.
     */
    public String getSaveFormat() throws DukeException {
        String isDoneSymbol = isDone ? "1" : "0";
        return String.format("%s | %s | %s", getClassSymbol(), isDoneSymbol, title);
    }

    /**
     * Retrieves the task class type and return the respective class symbol.
     *
     * @return The respective subclasses symbol.
     * @throws DukeException If the class return an invalid class type,
     * it will throw a DukeException.
     */
    public String getClassSymbol() throws DukeException {
        if (Todo.class.equals(this.getClass())) {
            return "T";
        } else if (Event.class.equals(this.getClass())) {
            return "E";
        } else if (Deadline.class.equals(this.getClass())) {
            return "D";
        } else {
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_TASK_TYPE);
        }

    }

    public boolean hasKeywords(String keywords) {
        return this.title.contains(keywords);
    }

    @Override
    public String toString() {
        String stateIcon = this.isDone ? "X" : " ";
        return String.format("[%s] %s", stateIcon, this.title);
    }
}
