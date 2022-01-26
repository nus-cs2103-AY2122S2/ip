package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Deadline timing. */
    private LocalDate by;
    private LocalTime byTime;

    /**
     * Constructor for Deadline Class.
     * @param description The description of the deadline.
     * @param by The timing of the deadline.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.by = by;
        this.byTime = null;
            if (isDone) {
                this.markAsDone();
            }
    }

    public Deadline(String description, LocalDate by, LocalTime byTime, boolean isDone) {
        super(description);
        this.by = by;
        this.byTime = byTime;
        if (isDone) {
            this.markAsDone();
        }
    }
        @Override
        public String writeToFile() {
            return " D " + super.writeToFile() + " | " + this.by + " | " + this.byTime;
    }
    /**
     * Returns the task in proper format.
     * @return String of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.byTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " " + ")";
    }
}
