package duke;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A skeleton for Tasks
 */
public abstract class Task {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("LLL dd yyyy");

    protected boolean isDone;
    protected String description;
    protected Optional<LocalDate> time;

    Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = Optional.empty();
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.time = Optional.empty();
    }

    Task(String description, LocalDate time) {
        this.description = description;
        this.time = Optional.<LocalDate>of(time);
    }

    Task(String description, boolean isDone, LocalDate time) {
        this.description = description;
        this.isDone = isDone;
        this.time = Optional.<LocalDate>of(time);
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String doneIndicator = " ";

        if (isDone) {
            doneIndicator = "X";
        }

        return "[" + doneIndicator + "] " + description;
    }
}
