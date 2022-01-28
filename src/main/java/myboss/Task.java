package myboss;

import java.util.Objects;

public class Task {
    boolean isDone;
    String taskName;
    String taskType;

    public Task(String taskName, String taskType) {
        this.isDone = false;
        this.taskName = taskName;
        this.taskType = taskType;
    }

    public Task(String taskName, String taskType, boolean isDone) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.isDone = isDone;
    }

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
