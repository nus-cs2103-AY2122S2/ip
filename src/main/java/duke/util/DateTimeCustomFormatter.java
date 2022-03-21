package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles to-and-fro parsing of LocalDateTime and String objects
 */
public final class DateTimeCustomFormatter {

    private static final DateTimeFormatter STRING_TO_DATE_FORMATTER = DateTimeFormatter.ofPattern(" dd/MM/yyyy HHmm");
    private static final DateTimeFormatter DATE_TO_STRING_FORMATTER = DateTimeFormatter.ofPattern(" dd/MM/yyyy HHmm");

    private DateTimeCustomFormatter() {
    }

    /**
     * Converts a String to LocalDateTime object
     *
     * @param dateTime String to be formatted
     * @return LocalDateTime object parsed from @param dateTime
     * @throws DateTimeParseException Occurs when LocalDateTime.parse() fails
     */
    public static LocalDateTime getDateFromString(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, STRING_TO_DATE_FORMATTER);
    }

    /**
     * Converts a LocalDateTime object to String
     *
     * @param dateTime LocalDateTime object to be formatted
     * @return String representing @param dateItem
     */
    public static String getStringFromDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_TO_STRING_FORMATTER);
    }
}
