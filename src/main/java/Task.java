import java.time.LocalDate;
import java.util.Optional;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Optional<LocalDate> date;

    Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = Optional.ofNullable(date);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public Optional<LocalDate> getDate() {
        return this.date;
    };

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
