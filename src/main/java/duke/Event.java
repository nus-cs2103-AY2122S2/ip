package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate time;

    Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = LocalDate.parse(time);
    }

    private String dateToString() {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    String getTaskType() {
        return "E";
    }

    String getDate() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String toString() {
        return String.format("[%s]", getTaskType())
                + super.toString()
                + String.format(" (at: %s)", dateToString());
    }
}