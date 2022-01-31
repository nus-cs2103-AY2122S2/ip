package istjbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a Deadline task with a description, and the date
 * at which the Deadline is due.
 */
public class Deadline extends Task {

    /**
     * Constructor for this Deadline task.
     *
     * @param description Description for the task.
     * @param by String representation of the due date of the deadline.
     * @throws DateTimeParseException When the date format is incorrect.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description, LocalDate.parse(by));
    }

    private String dateToString() {
        String formattedDate = this.date.orElseThrow().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return formattedDate;
    }

    /**
     * Returns a String with a format for the txt file that is to be saved.
     *
     * @return Txt-file formatted String.
     */
    @Override
    public String toTxtString() {
        String marked = this.isDone? "1" : "0";
        String txtString = "deadline / " + marked + " / " + this.description + " / "
                + this.date.orElseThrow().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return txtString;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + ")";
    }
}
