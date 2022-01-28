package myboss;

import java.util.Objects;

/**
 * Represents a task. A Task Object corresponds to a task represented by a name, type
 * and whether it is done or not.
 */
public class Task {
    boolean isDone;
    String taskName;
    String taskType;

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
            return  " Nice! I've marked this task as done:" + "\n" + "  " +
                    "     [" + (this.isDone ? "X" : " ") + "] " +
                    this.taskName;
        } else {
            this.isDone = false;
            return "OK, I've marked this task as not done yet:" + "\n" + "  " +
                    "     [" + (this.isDone ? "X" : " ") + "] " +
                    this.taskName;
        }
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " +
                this.taskName + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone
                && Objects.equals(taskName, task.taskName)
                && Objects.equals(taskType, task.taskType);
    }
}
