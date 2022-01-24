import java.io.Serializable;

public class Task implements Serializable {
    public String taskName;
    public boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String getStatus() {
        return done ? "[X] " : "[ ] ";
    }

    @Override
    public String toString() {
        return this.getStatus() + this.taskName;
    }
}
