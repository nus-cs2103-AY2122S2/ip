import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markComplete() {
        this.isDone = true;
    }

    public void markIncomplete() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "["+ this.getStatusIcon() + "] " + description;
    }
}
