import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {

    protected String at;
    protected LocalTime localTime;
    protected LocalDate atLocalDate;
    protected DateTimeFormatter dTF;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (this.at.length() > 10) {
            DateTimeFormatter twelveHourFormatter = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
            String[] dateAndTime = Parser.splitDateAndTime(this.at);
            this.localTime = LocalTime.parse(dateAndTime[1], twelveHourFormatter);
            this.atLocalDate = LocalDate.parse(dateAndTime[0]);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        } else {
            this.atLocalDate = LocalDate.parse(at);
            this.dTF = DateTimeFormatter.ofPattern("dd MMM yyyy");
        }
    }

    public boolean hasTime() {
        return this.localTime != null;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dTF.format(atLocalDate) + (hasTime() ? ", " + localTime.toString() + ")" : ")");
    }
}
//testing
