package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime {

    private final LocalDate date;
    private final LocalTime time;

    public static DukeDateTime parse(String s) throws DukeException {
        String[] splited = s.split(" ");
        DukeDateTime datetime;
        try {
            if (splited.length == 2) {
                datetime = new DukeDateTime(LocalDate.parse(splited[0],
                        DateTimeFormatter.ofPattern("yyyy-M-d")),
                        LocalTime.parse(splited[1],
                                DateTimeFormatter.ofPattern("H:mm")));
            } else {
                datetime = new DukeDateTime(LocalDate.parse(splited[0],
                        DateTimeFormatter.ofPattern("yyyy-M-d")));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unrecognized time format. Valid formats:\nyyyy-M-d\nyyyy-M-d H:mm");
        }
        return datetime;
    }

    public DukeDateTime(LocalDate date) {
        this.date = date;
        this.time = null;
    }

    public DukeDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DukeDateTime)) {
            return false;
        }
        DukeDateTime other = (DukeDateTime) o;
        if (this.hasTime()) {
            return this.date.isEqual(other.date) && this.time.equals(other.time);
        }
        return this.date.isEqual(other.date);
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
