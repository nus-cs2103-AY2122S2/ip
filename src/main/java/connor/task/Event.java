package connor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected boolean hasLocalDate = false;

    public Event(String desc, String at) {
        super(desc, TaskType.EVENT);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public Event(String desc, LocalDate date) {
        super(desc, TaskType.EVENT);
        this.date = date;
        this.at = date.toString();
        this.hasLocalDate = true;
    }

    public String getDate() {
        if (hasLocalDate) {
            // Of the form "June 24, 2019"
            return date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        } else {
            return at;
        }
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (At: " + getDate() + ")";
    }
}
