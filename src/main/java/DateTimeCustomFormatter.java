import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class DateTimeCustomFormatter {

    private DateTimeCustomFormatter() {
    }

    private static final DateTimeFormatter STRING_TO_DATE_FORMATTER = DateTimeFormatter.ofPattern(" dd/MM/yyyy HHmm");

    private static final DateTimeFormatter DATE_TO_STRING_FORMATTER = DateTimeFormatter.ofPattern(" dd/MM/yyyy HHmm");

    public static LocalDateTime getDateFromString(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime, STRING_TO_DATE_FORMATTER);
    }

    public static String getStringFromDate(LocalDateTime dateTime) {
        return dateTime.format(DATE_TO_STRING_FORMATTER);
    }
}