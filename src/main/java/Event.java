import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task{

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        int year = at.getYear();
        String month = super.months[at.getMonthValue() - 1];
        int day = at.getDayOfMonth();

        String hour;
        if (at.getHour() < 10) {
            hour = "0" + at.getHour();
        } else {
            hour = String.valueOf(at.getHour());
        }

        String min;
        if (at.getMinute() < 10) {
            min = "0" + at.getMinute();
        } else {
            min = String.valueOf(at.getMinute());
        }

        return "[E]"
                + super.toString()
                + " (at: " + month + " " + day + " " + year + " " + hour + ":" + min + ")";
    }
}