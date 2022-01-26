
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    char type;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'd';
    }

    public String getDate() {
        LocalDate date = LocalDate.parse(by.substring(3, 13));
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String dateFormatted = date.format(newFormat);
        return dateFormatted;
    }

    public String getTime() {
        String timeclock = by.substring(14);
        return timeclock;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + " " + this.getTime() + ")";
    }
}
