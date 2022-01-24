import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static DateTimeFormatter fromStringToDate = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy[ HH:mm:ss]"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy[ HH:mm:ss]"))
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy[ HH:mm:ss]"))
            .toFormatter();
    private static DateTimeFormatter fromDateToString = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static LocalDateTime toLocalDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, fromStringToDate);
    }

    public static String toString(LocalDateTime date) {
        return date.format(fromDateToString);
    }
}
