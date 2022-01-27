package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    public String convertDate(String dueDate) {
        try {
            LocalDate date = LocalDate.parse(dueDate);
            String year = String.valueOf(date.getYear());
            String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            String day = String.valueOf(date.getDayOfMonth());
            return month + " " + day + " " + year;
        }
        catch (DateTimeParseException e) {
            return dueDate;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDate(this.dueDate) + ")";
    }
}
