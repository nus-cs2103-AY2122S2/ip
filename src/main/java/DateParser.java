import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateParser {
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
