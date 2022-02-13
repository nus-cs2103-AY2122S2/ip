package duke;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Parser {
    /**
     * Parses a date-time String.
     *
     * @param dateTime The date-time String.
     * @return The parsed LocalDateTime.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d[ HHmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        try {
            return LocalDateTime.parse(dateTime, formatter);

        } catch (DateTimeParseException e) {
            throw new DukeException(dateTime + " is not a valid date. Example: 2022-3-15 1630");
        }
    }

    /**
     * Parses a duration String.
     *
     * @param duration The duration String.
     * @return The parsed Duration.
     */
    public static Duration parseDuration(String duration) {
        try {
            return Duration.parse("PT" + duration);
        } catch (DateTimeParseException e) {
            throw new DukeException(duration + " is not a valid duration. Example: 1h5m");
        }
    }
}
