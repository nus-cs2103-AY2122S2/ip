package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that contains information for all tasks.
 */
public class Task {
    String description;
    boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void done() {
        isDone = true;
    }

    public void undo() {
        isDone = false;
    }
}

/**
 * Todo class that inherits from the Task class.
 */
class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}

/**
 * Event class that inherits from the Task class.
 */
class Event extends Task {
    LocalDate when;

    public Event(String desc, LocalDate date) {
        super(desc);
        when = date;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

    /**
     * Deadline class that inherits from the Task class.
     */
    class Deadline extends Task {
        LocalDate when;

        public Deadline(String desc, LocalDate date) {
            super(desc);
            when = date;
        }

        public String toString() {
            return "[D]" + super.toString() + " (by: " +
                    when.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
