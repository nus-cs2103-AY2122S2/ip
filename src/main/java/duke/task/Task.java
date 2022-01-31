package duke.task;

import duke.dukeexceptions.InvalidDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected static DateTimeFormatter initFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    protected String taskName;
    protected boolean isMarked;

    protected Task(String taskName, boolean done) {
        this.taskName = taskName;
        this.isMarked = done;
    }

    public void setDone() {
        isMarked = true;
    }

    public void setUndone() {
        isMarked = false;
    }

    public abstract String updateIntoDatabase();

    public static Task createTask(String type, Boolean done, String name, String date) throws InvalidDate {
        // Convert the task type to uppercase.
        type = type.toUpperCase();

        // Creates a new task based on the task type.
        if (type.equals("TODO")) {
            return new ToDo(name, done);
        } else if (type.equals("DEADLINE")) {
            return new Deadline(name, done, date);
        } else if (type.equals("EVENT")) {
            return new Event(name, done, date);
        } else {
            throw new IllegalArgumentException();
        }
    };
}

final class ToDo extends Task {
    protected ToDo(String taskName, boolean done) {
        super(taskName, done);
    }

    @Override
    public String toString() {
        String tag = "[T]";
        String doneIndicator = "[" + (isMarked ? "X" : " ") + "]";
        return tag + doneIndicator + " " + taskName;
    }

    public String updateIntoDatabase() {
        return "TODO\n" + isMarked + "\n" + taskName + "\n" + "*** Next Task ***\n";
    }
}

final class Deadline extends Task {
    private LocalDateTime deadline;

    protected Deadline(String taskName, boolean done, String deadline) throws InvalidDate {
        super(taskName, done);

        // Parse the date of the deadline as DD MMM YYYY HH:mm.
        try {
            this.deadline = LocalDateTime.parse(deadline, initFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDate();
        }
    }

    @Override
    public String toString() {
        String tag = "[D]";
        String doneIndicator = "[" + (isMarked ? "X" : " ") + "]";
        String deadline = "(by: " + this.deadline.format(formatter) + ")";
        return tag + doneIndicator + " " + taskName + deadline;
    }

    public String updateIntoDatabase() {
        return "DEADLINE\n" + isMarked + "\n" + taskName + "\n" + deadline.format(initFormatter) + "\n"
                + "*** Next Task ***\n";
    }
}

final class Event extends Task {
    private LocalDateTime eventDate;

    protected Event(String taskName, boolean done, String eventDate) throws InvalidDate {
        super(taskName, done);

        // Parse the date of the deadline as DD MMM YYYY HH:mm.
        try {
            this.eventDate = LocalDateTime.parse(eventDate, initFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDate();
        }
    }

    @Override
    public String toString() {
        String tag = "[E]";
        String doneIndicator = "[" + (isMarked ? "X" : " ") + "]";
        String eventDate = "(at: " + this.eventDate.format(formatter) + ")";
        return tag + doneIndicator + " " + taskName + eventDate;
    }

    public String updateIntoDatabase() {
        return "EVENT\n" + isMarked + "\n" + taskName + "\n" + eventDate.format(initFormatter) + "\n"
                + "*** Next Task ***\n";
    }
}