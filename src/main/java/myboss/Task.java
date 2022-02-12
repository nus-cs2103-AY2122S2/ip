package myboss;

import java.util.Objects;

/**
 * Represents a task. A Task Object corresponds to a task represented by a name, type
 * and whether it is done or not.
 */
public class Task {
    private boolean isDone;
    private String taskName;
    private String taskType;
    private Priority priorityLevel;

    /**
     * Creates a Task Object with the specified task name and task type.
     *
     * @param taskName name of task.
     * @param taskType type of task.
     */
    public Task(String taskName, String taskType) {
        this.isDone = false;
        this.taskName = taskName;
        this.taskType = taskType;
        this.priorityLevel = Priority.LOW;
    }

    /**
     * Creates a Task Object with the specified task name, task type and whether task is done.
     *
     * @param taskName name of task.
     * @param taskType type of task.
     * @param isDone whether task is done.
     */
    public Task(String taskName, String taskType, boolean isDone) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
        this.priorityLevel = Priority.LOW;
    }

    /**
     * Creates a Task Object with the specified task name, task type and whether task is done.
     *
     * @param taskName name of task.
     * @param taskType type of task.
     * @param isDone whether task is done.
     */
    public Task(String taskName, String taskType, boolean isDone, Priority priorityLevel) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
        this.priorityLevel = priorityLevel;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public Priority getPriorityLevel() {
        return this.priorityLevel;
    }

    public void changePriorityLevel(String priorityLevelString) throws MyBossException {
        this.priorityLevel = convertPriorityLevel(priorityLevelString);
    }

    /**
     * Returns the priority level converted from string.
     *
     * @param priorityLevelString string representation of the priority level to be converted.
     * @return priority converted from string.
     * @throws MyBossException if priority argument is invalid.
     */
    public Priority convertPriorityLevel(String priorityLevelString) throws MyBossException {
        try {
            return Priority.valueOf(priorityLevelString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MyBossException("Priority argument invalid!");
        }
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns the string output of a task when marked as done or not done.
     *
     * @param isDone whether task is to be marked as done or not done.
     * @return string output of the task when marked as done or not done.
     */
    public String markAsDone(boolean isDone) {
        if (isDone) {
            this.isDone = true;
            return "Nice! I've marked this task as done:" + "\n" + "  "
                    + "    [" + (this.isDone ? "X" : " ") + "] "
                    + this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  "
                    + "    [" + (this.isDone ? "X" : " ") + "] "
                    + this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + this.priorityLevel + "]"
                + "[" + this.taskType + "]"
                + "[" + (isDone ? "X" : " ") + "] "
                + this.taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone
                && Objects.equals(taskName, task.taskName)
                && Objects.equals(taskType, task.taskType)
                && priorityLevel == task.priorityLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isDone, taskName, taskType, priorityLevel);
    }
}

enum Priority {
    HIGH ("High"),
    MED("Med"),
    LOW ("Low");

    private final String priorityLevel;

    Priority(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String toString() {
        return priorityLevel;
    }
}
