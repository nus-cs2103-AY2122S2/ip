import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

abstract class Task {
    protected static DateTimeFormatter initFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    protected String taskName;
    protected boolean done;

    Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    void setDone() {
        this.done = true;
    }

    void setUndone() {
        this.done = false;
    }
}

final class ToDo extends Task {

    ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        String tag = "[T]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        return tag + doneIndicator + " " + this.taskName;
    }
}

final class Deadline extends Task {
    LocalDateTime deadline;

    Deadline(String taskName, String deadline) throws InvalidDate {
        super(taskName);
        try {
            this.deadline = LocalDateTime.parse(deadline, initFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDate();
        }

    }

    @Override
    public String toString() {
        String tag = "[D]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String deadline = "(by: " + this.deadline.format(formatter) + ")";
        return tag + doneIndicator + " " + this.taskName + deadline;
    }
}

final class Event extends Task {
    LocalDateTime eventDate;

    Event(String taskName, String eventDate) throws InvalidDate {
        super(taskName);
        try {
            this.eventDate = LocalDateTime.parse(eventDate, initFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDate();
        }

    }

    @Override
    public String toString() {
        String tag = "[E]";
        String doneIndicator = "[" + (this.done ? "X" : " ") + "]";
        String eventDate = "(at: " + this.eventDate.format(formatter) + ")";
        return tag + doneIndicator + " " + this.taskName + eventDate;
    }
}