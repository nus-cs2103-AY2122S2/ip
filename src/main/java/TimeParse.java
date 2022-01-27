import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class parse a varying 4 digit military time into a 12 hour LocalTime object.
 */
public class TimeParse {
    private String timeStr;
    private LocalTime time;

    public TimeParse(String str) {
        this.timeStr = str.substring(0, 2) + ":" + str.substring(2);
        time = LocalTime.parse(timeStr);
        this.helper();
    }

    private LocalTime helper() {
        return time;
    }

    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

}
