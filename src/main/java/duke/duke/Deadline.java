package duke.duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {

    protected String dueDate;

    /**
     * Initializes a new Deadline class with the name and its due date.
     *
     * @param name The name of the Deadline needed by the user.
     * @param dueDate The date when the Deadline is due.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Converts a date that starts in the format of YYYY-MM-DD, and formats
     * it to DD Mon YYYY.
     *
     * @param dueDate Date in format of YYYY-MM-DD
     * @return Formatted date
     */
    public String convertDate(String dueDate) {
        try {
            LocalDate date = LocalDate.parse(dueDate);
            String year = String.valueOf(date.getYear());
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String day = String.valueOf(date.getDayOfMonth());
            return month + " " + day + " " + year;
        } catch (DateTimeParseException e) {
            return dueDate;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDate(this.dueDate) + ")";
    }
}
