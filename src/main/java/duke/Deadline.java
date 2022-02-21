package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task
 */

public class Deadline extends Task {

    protected LocalDate by;
    protected String time;
    public boolean isDone;

    /**
     * Constructor for a deadline task
     * @param description description of deadline
     * @param by date of the deadline
     * @param time time of the deadline
     */
    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.by = by;
        this.time = time;
        this.isDone = false;
    }

    /**
     * Constructor for deadline where
     * it can be indicated whether deadline is done or not
     */
    public Deadline(String description, LocalDate by, String time, boolean isDone) {
        super(description);
        this.by = by;
        this.time = time;
        this.isDone = isDone;
    }

    /**
     * Outputs the deadline in a format that can be saved
     * @return Deadline as a string to be saved
     */
    public String toSave() {
        int isDoneNumber;
        if(isDone) {
            isDoneNumber = 1;
        } else {
            isDoneNumber = 0;
        }
        return "D | " + isDoneNumber + " | " + description +
                "| " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + time + System.lineSeparator();
    }

    /**
     * Returns deadline as a string
     * @return Deadline as a string
     */
    @Override
    public String toString() {
     return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) +" " + time + ")";
    }
}
