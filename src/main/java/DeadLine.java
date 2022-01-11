import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    protected String by;
    protected LocalDate bydate;
    protected LocalTime bytime;

    public DeadLine(String objective, String date, String time) {
        super(objective);
        this.bydate = LocalDate.parse(date);
        this.bytime = LocalTime.parse(time);
    }

    public boolean sameTime(String date) {
        return this.bydate.equals(LocalDate.parse(date));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.bydate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.bytime.format(DateTimeFormatter.ofPattern("HH:mm a")) + ")";
    }
}