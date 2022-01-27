package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Deadline timing. */
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Constructor for Deadline Class.
     * @param description The description of the deadline.
     * @param by The timing of the deadline.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description);
        this.deadlineDate = by;
        this.deadlineTime = null;
        if (isDone) {
            this.markAsDone();
        }
    }

    public Deadline(String description, LocalDate by, LocalTime byTime, boolean isDone) {
        super(description);
        this.deadlineDate = by;
        this.deadlineTime = byTime;
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String writeToFile() {
        return " D " + super.writeToFile() + " | " + this.deadlineDate + " | " + this.deadlineTime;
    }

    /**
     * Returns the task in proper format.
     * @return String of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + this.deadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " )";
    }
}
