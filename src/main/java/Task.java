import java.util.Set;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String markAsDoneFeedback = "Nice! I've marked this task as done: \n ";
    protected String markAsUndoneFeedback = "OK, I've marked this task as not done yet: \n ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
