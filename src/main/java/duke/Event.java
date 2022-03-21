package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Task of type Event, includes the date.
 */

public class Event extends Task {
    private final String sym = "E";
    private final LocalDate dayAndTime;
    private String dayString;

    /**
     * Constructor for Event
     *
     * @param description - description of the task
     * @param dayAndTime - The date of the task in this format "yyyy-mm-dd"
     */
    Event (String description, String dayAndTime) throws DateTimeException {
        super(description);
        this.dayAndTime = LocalDate.parse(dayAndTime);
        this.dayString = dayAndTime;
    }

    /**
     * Retreives the symbol of Task
     */
    public String getSym() {
        return sym;
    }

    /**
     * Retreives the day of Task
     */
    public String getDayString() {
        return dayString;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at:%s)", sym, super.getStatusIcon(), super.getDescription(),
                    this.dayAndTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }
}
