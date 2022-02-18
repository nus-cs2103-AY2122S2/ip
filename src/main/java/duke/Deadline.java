package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Task of type Deadline with a day.
 */
public class Deadline extends Task {
    private final String sym = "D";
    private LocalDate day;
    private String dayString;

    /**
     * Constructor for a Deadline
     *
     * @param description The details of the task
     * @param day The deadline of the task in the format "yyyy-mm-dd"
     */
    Deadline (String description, String day) {
        super(description);
        this.day = LocalDate.parse(day);
        this.dayString = day;
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
        return String.format("[%s][%s]%s (by:%s)", sym, super.getStatusIcon(), super.getDescription(),
                day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }
}
