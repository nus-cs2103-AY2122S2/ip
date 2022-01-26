package duke.task;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.Message.SPACE;

/**
 * A basic class represents for a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of the Task
     * @return "X" if done else blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * A method to mark task as Done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * A method to unmark done task
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return Task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return String representation of Task
     */
    @Override
    public String toString() {
        return OPEN_BRACKET + this.getStatusIcon() + CLOSE_BRACKET + SPACE + this.getDescription();
    }
}
