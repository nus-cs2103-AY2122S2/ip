//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class which inherits from Task.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    /**
     * The constructors for deadline class
     * @param description The description of task sent by the user
     * @param by The deadline which the task should be completed by
     */
    public Deadline(String description, LocalDate date, String time, String type) {
        super(description, type);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "  + time + ")";
    }
}