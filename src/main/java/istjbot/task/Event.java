package istjbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an Event task with a description, and the date
 * at which the Event will occur.
 */
public class Event extends Task {

    /**
     * Constructor for this Event task.
     *
     * @param description Description for the task.
     * @param at String representation of the date of the event.
     * @throws DateTimeParseException When the date format is incorrect.
     */
    Event(String description, String at) throws DateTimeParseException {
        super(description, LocalDate.parse(at));
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
        String txtString = "event / " + marked + " / " + this.description + " / "
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
        return "[E]" + super.toString() + " (at: " + this.dateToString() + ")";
    }
}
