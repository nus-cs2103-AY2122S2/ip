package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;


    /**
     * Creates a Deadline task with a date as deadline.
     *
     * @param description Description of task.
     * @param by Date as deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        List<String> dateFormats = Arrays.asList("yyyy-MM-dd", "dd-MM-yyyy", "dd/MM/yyyy", "dd:MM:yyyy");

        DateTimeFormatter formatToParse;
        for (int i = 0; i <= dateFormats.size(); i++) {
            try {
                formatToParse = DateTimeFormatter.ofPattern(dateFormats.get(i));
                this.date = LocalDate.parse(by, formatToParse);
                break;
            } catch (Exception wrongFormat) {
                if (i == dateFormats.size()) {
                    throw new DateTimeException("Date invalid");
                }
                continue;
            }
        }
    }


    /**
     * Returns formatted string representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
