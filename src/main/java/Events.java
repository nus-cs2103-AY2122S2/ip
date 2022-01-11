import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected String at;
    protected LocalDate date;
    protected LocalTime fromtime;
    protected LocalTime bytime;

    public Events(String objective, String date, String from, String to) {
        super(objective);
        this.date = LocalDate.parse(date);
        this.fromtime = LocalTime.parse(from);
        this.bytime = LocalTime.parse(to);
    }

    public boolean sameTime(String date) {
        return this.date.equals(LocalDate.parse(date));
    }
    public Events(String objective, Boolean done, String at) {
        super(objective, done);
        this.at = at;
    }
    @Override
    public String serialize() {
        return "E|" + (this.done ? "1|" : "0|") + this.objective + "|" + this.at +"\n";
    };
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.fromtime.format(DateTimeFormatter.ofPattern("HH:mm a")) + " - "
                + this.bytime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                + ")";
    }
}
