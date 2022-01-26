import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    protected String by;
    protected LocalTime localTime;
    protected LocalDate atLocalDate;
    protected DateTimeFormatter dTF;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        if (this.by.length() > 10) {
            DateTimeFormatter twelveHourFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
            String[] dateAndTime = Parser.splitDateAndTime(this.by);
            this.localTime = LocalTime.parse(dateAndTime[1], twelveHourFormatter);
            this.atLocalDate = LocalDate.parse(dateAndTime[0]);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        } else {
            this.atLocalDate = LocalDate.parse(this.by);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        }
    }

    public boolean hasTime() {
       return this.localTime != null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dTF.format(atLocalDate) + (hasTime() ? ", " + localTime.toString() + ")" : ")");
    }
}
