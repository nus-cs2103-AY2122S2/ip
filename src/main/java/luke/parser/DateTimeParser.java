package luke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * DateTime Parser with specific format.
 * Accepts numerous format as input, but output format only as [MMM dd yyyy HH:mm] for strings
 * and [dd/MM/yyyy HH:mm] for storing.
 */
public class DateTimeParser {

    /**
     * Formatter containing all acceptable datetime format that user can pass as string.
     */
    private static DateTimeFormatter fromStringToDate = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy.MM.dd HHmm"))
            .toFormatter();

    /**
     * Specific format [MMM dd yyyy HH:mm] to convert all date to string.
     */
    private static DateTimeFormatter fromDateToString = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Specific format [dd/MM/yyyy HH:mm] to convert all date to command string.
     */
    private static DateTimeFormatter fromDateToCommand = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDateTime toLocalDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, fromStringToDate);
    }

    public static String toString(LocalDateTime date) {
        return date.format(fromDateToString);
    }

    public static String toCommandString(LocalDateTime date) {
        return date.format(fromDateToCommand);
    }
}
