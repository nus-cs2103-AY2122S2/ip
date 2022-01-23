import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime {

    private final LocalDate date;
    private final LocalTime time;

    public static DukeDateTime parse(String s) {
        String[] splited = s.split(" ");
        DukeDateTime datetime = new DukeDateTime();
        if (splited.length == 2) {
            datetime = new DukeDateTime(LocalDate.parse(splited[0],
                    DateTimeFormatter.ofPattern("yyyy-M-d")),
                    LocalTime.parse(splited[1],
                            DateTimeFormatter.ofPattern("H:mm")));
        } else {
            datetime = new DukeDateTime(LocalDate.parse(splited[0],
                    DateTimeFormatter.ofPattern("yyyy-M-d")));
        }
        return datetime;
    }

    public DukeDateTime() {
        this.date = null;
        this.time = null;
    }

    public DukeDateTime(LocalDate date) {
        this.date = date;
        this.time = null;
    }

    public DukeDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public String format(String pattern_date) {
        String res = date.format(DateTimeFormatter.ofPattern(pattern_date));
        if (hasTime()) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return res;
    }

    public boolean hasTime() {
        return time != null;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

}
