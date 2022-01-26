import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
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
    private static DateTimeFormatter fromDateToString = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
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
