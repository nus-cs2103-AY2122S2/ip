import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d HH:mm"))
            .toFormatter();
    private static final DateTimeFormatter STRING_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public static LocalDateTime stringToDate(String dateTime) throws DateTimeParseException {
            return LocalDateTime.parse(dateTime, FORMATTER);
    }

    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(STRING_FORMATTER);
    }
}
