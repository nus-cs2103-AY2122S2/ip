import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DateTimeParser {

    public static LocalDate parseDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        //System.out.println(parsedDate);

        return parsedDate;
    }

    public static String formatDate(LocalDate date) {
        //System.out.println(date);
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

    }


}
