package connor.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected boolean hasLocalDate = false;

    public Deadline(String desc, String by) {
        super(desc, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String desc, LocalDate date) {
        super(desc, TaskType.DEADLINE);
        this.date = date;
        this.by = date.toString();
        this.hasLocalDate = true;
    }

    public String getBy() {
        return by;
    }

    public boolean hasLocalDate() {
        return hasLocalDate;
    }

    private String getDate() {
        if (hasLocalDate) {
            // Of the form "June 24, 2019"
            return date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
        } else {
            return by;
        }
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (By: " + getDate() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Deadline)) {
            return false;
        } else {
            Deadline d = (Deadline) o;
            return this.getDesc().equals(d.getDesc())
                    && this.isDone().equals(d.isDone())
                    && this.getDate().equals(d.getDate());
        }
    }
}
