package batman.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
            .toFormatter();
    private static final DateTimeFormatter STRING_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Converts String of datetime to a DateTime object.
     *
     * @param str user's input datetime.
     * @return LocalDateTime object.
     * @throws DateTimeParseException check if the datetime string
     *                                  matches the given format.
     */
    public static LocalDateTime stringToDate(String str) throws DateTimeParseException {
        return LocalDateTime.parse(str, FORMATTER);
    }

    /**
     * Converts DateTime object to String.
     *
     * @param dateTime user's input datetime.
     * @return String object.
     */
    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(STRING_FORMATTER);
    }
}
