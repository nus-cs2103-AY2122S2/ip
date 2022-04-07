package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * Creates a event task.
     *
     * @param description Description of the event task.
     * @param at The String representation of the date of the event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        List<String> dateFormats = Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "dd:MM:yyyy");

        DateTimeFormatter formatToParse;
        for (int i = 0; i <= dateFormats.size(); i++) {
            try {
                formatToParse = DateTimeFormatter.ofPattern(dateFormats.get(i));
                this.date = LocalDate.parse(at, formatToParse);
                break;
            } catch (Exception wrongFormat) {
                if (i == dateFormats.size()) {
                    throw new DateTimeException("Date invalid");
                }
                continue;
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
