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

    public Event(String desc, LocalDate date) {
        super(desc, TaskType.EVENT);
        this.date = date;
        this.at = date.toString();
        this.hasLocalDate = true;
    }

    public String getAt() {
        return at;
    }

    public boolean hasLocalDate() {
        return hasLocalDate;
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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event e = (Event) o;
            return this.getDesc().equals(e.getDesc())
                    && this.isDone().equals(e.isDone())
                    && this.getDate().equals(e.getDate());
        }
    }
}
