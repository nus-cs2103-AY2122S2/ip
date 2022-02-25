package jarvis.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public static final String TASK_CODE = "E";

    protected LocalDate at;

    /**
     * Constructor for the deadline object.
     *
     * @param description description of the task
     * @param at the deadline for the task
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String getDateString() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get the csv representation of the task.
     *
     * @return string in csv format
     */
    public String toCsv(String delimiter) {
        return String.format("%s,%s,%s,%s", TASK_CODE, this.getStatusIcon(), this.getDescription(), at);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", TASK_CODE, super.toString(), getDateString());
    }
}
