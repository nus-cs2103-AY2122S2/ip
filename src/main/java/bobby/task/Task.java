package bobby.task;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable, Comparable<Task> {
    private String taskName;
    private boolean isDone;
    private LocalDate date;

    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
        date = null;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return getStatus() + taskName;
    }

    @Override
    public int compareTo(Task t) {
        if (this.date == null || t.date == null) {
            if (this.date == null && t.date == null) {
                return 0;
            } else if (this.date == null) {
                return 1;
            } else {
                return -1;
            }
        } else if (this.date.isBefore(t.date)) {
            return -1;
        } else if (this.date.isAfter((t.date))) {
            return 1;
        } else if (this.date.isEqual(t.date)) {
            return 0;
        }
        return 0;
    }
}
