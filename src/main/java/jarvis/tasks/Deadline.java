package jarvis.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends jarvis.tasks.Task {
    public static final String TASK_CODE = "D";

    protected LocalDate by;

    /**
     * Constructor for the deadline object.
     *
     * @param description description of the task
     * @param by the deadline for the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getDateString() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get the csv representation of the task.
     *
     * @return string in csv format
     */
    public String toCsv(String delimiter) {
        return String.format("%s,%s,%s,%s", TASK_CODE, this.getStatusIcon(), this.getDescription(), by);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", TASK_CODE, super.toString(), getDateString());
    }
}
