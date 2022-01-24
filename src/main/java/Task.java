import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("d/M/uuuu");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void mark() {
        this.isDone = true;

    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}