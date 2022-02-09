package duke.managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeManager {
    static String localDateTimeFormat = "dd/MM/yyyy HHmm";
    static String localDateTimeString = "E, dd MMM yyyy HH:mm";

    static public LocalDateTime parseString(String dateTimeString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(localDateTimeFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, inputFormatter);
        return localDateTime;
    }

    static public String getDisplayString(LocalDateTime localDateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(localDateTimeString);
        return localDateTime.format(outputFormatter);
    }

    static public String getOriginalString(LocalDateTime localDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(localDateTimeFormat);
        return localDateTime.format(inputFormatter);
    }
}
