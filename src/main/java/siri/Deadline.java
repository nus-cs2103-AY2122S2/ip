package siri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents the deadline task
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String deadline;

    public Deadline(String description, String initialLetter, String deadline) {
        super(description, initialLetter);
        this.deadline = deadline;
        //find a way to scan deadline after description entered by user, maybe use the "/" as delimiter for the scanner?
    }

    //reused from Brigette Santoso E0564307 Level-8 source code
    public Deadline(String description, String initialLetter, LocalDate date) {
        super(description, initialLetter);
        this.deadline = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] "
                + this.description
                + " (by: " + this.deadline + ")";
    }

}
