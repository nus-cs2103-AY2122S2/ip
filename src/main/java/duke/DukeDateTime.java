package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DukeDateTime {

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Obtains an instance of {@code DukeDateTime} from a text string.
     * @param text the text to parse, not null
     * @return the parsed {@code DukeDateTime}, not null
     * @throws DukeException if the text cannot be parsed
     */
    public static DukeDateTime parse(String text) throws DukeException {
        String[] splited = text.split(" ");
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

    /**
     * Constructs a {@code DukeDateTime} object with date but not time component.
     * @param date A LocalDate object
     */
    public DukeDateTime(LocalDate date) {
        this.date = date;
        this.time = null;
    }

    /**
     * Constructs a {@code DukeDateTime} object with both date and time components.
     * @param date A LocalDate object
     * @param time A LocalTime object
     */
    public DukeDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Checks if this {@code DukeDateTime} is equal to another DukeDateTime.
     * @Override {@code equals} in class {@code Object}
     * @param obj the object to check, null returns false
     * @return true if both objects are DukeDateTime and either<p>
     *   1. Both do not have the time component and refer to the same date, or<p>
     *   2. Both have the time component and refer to the same time
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DukeDateTime)) {
            return false;
        }
        DukeDateTime other = (DukeDateTime) obj;
        if (this.hasTime() || other.hasTime()) {
            return this.date.isEqual(other.date) && this.time.equals(other.time);
        }
        return this.date.isEqual(other.date);
    }

    /**
     * Formats this {@code DukeDateTime} using the specified pattern for date and "H:mm" for time.
     * @param pattern_date the pattern to use for date, not null
     * @return the formatted DukeDateTime string, not null
     */
    public String format(String pattern_date) {
        String res = date.format(DateTimeFormatter.ofPattern(pattern_date));
        if (hasTime()) {
            res += " " + time.format(DateTimeFormatter.ofPattern("H:mm"));
        }
        return res;
    }

    /**
     * Checks if this {@code DukeDateTime} has the time component.
     * @return true if this {@code DukeDateTime} has the time component
     */
    public boolean hasTime() {
        return time != null;
    }

    /**
     * Gets the date component of this {@code DukeDateTime} as a {@code LocalDate} object.
     * @return a {@code LocalDate} object, not null
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the time component of this {@code DukeDateTime} as a {@code LocalTime} object,
     *   returns null if this {@code DukeDateTime} does not have the time component.
     * @return a {@code LocalTime} object, or {@code null} if this {@code DukeDateTime}
     *   does not have the time component
     */
    public LocalTime getTime() {
        return time;
    }

}
