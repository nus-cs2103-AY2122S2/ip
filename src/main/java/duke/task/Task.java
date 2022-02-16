package duke.task;

import duke.DukeException;
import duke.common.Messages;
import duke.common.TaskType;

/**
 * A class that represents a task.
 */
public class Task {
    private final TaskType taskType;
    private final String title;
    private boolean isDone;

    /**
     * Creates a Task instance with a title.
     *
     * @param title The title of the task.
     */
    public Task(String title, TaskType taskType) {
        this.title = title;
        this.isDone = false;
        this.taskType = taskType;
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
        assert this.taskType != TaskType.TASK : "TaskType of Task should not appear here.";
        switch (this.taskType) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            throw new DukeException(Messages.MESSAGE_ERROR_INVALID_TASK_TYPE);
        }
    }

    public boolean hasKeywords(String keywords) {
        return this.title.contains(keywords);
    }

    public boolean hasSameTaskType(TaskType taskType) throws DukeException {
        return this.getClassSymbol().equals(taskType.getTaskTypeSymbol());
    }

    @Override
    public String toString() {
        String stateIcon = this.isDone ? "X" : " ";
        return String.format("[%s] %s", stateIcon, this.title);
    }
}
