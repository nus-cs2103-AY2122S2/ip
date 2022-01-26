import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable, Comparable<Task> {
    protected String taskName;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        date = null;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
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
        return this.getStatus() + this.taskName;
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
