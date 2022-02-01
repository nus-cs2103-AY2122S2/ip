package seedu.duke;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    protected static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("LLL dd yyyy");

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
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String tempStr = " ";

        if (this.isDone) {
            tempStr = "X";
        }

        return "[" + tempStr + "] " + this.description;
    }
}
