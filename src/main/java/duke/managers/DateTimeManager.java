package duke.managers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Manage the conversion and parsing of string into Datetime.
 * Any conversion of string into Datetime and vice versa should be done via the DateTimeManager.
 * @see <a href="https://www.baeldung.com/java-datetimeformatter">more about the format.
 */
public class DateTimeManager {
    static String localDateTimeFormat = "dd/MM/yyyy HHmm";
    static String localDateTimeString = "EEEE, dd MMM yyyy HH:mm";

    /**
     * Parse a string (representing a datetime) to a LocalDateTime. The form of the string needs to be in "dd/MM/yyyy HHmm".
     * @param dateTimeString a datetime string in form of "dd/MM/yyyy HHmm"
     *                       see <a href="https://www.baeldung.com/java-datetimeformatter">here for more about the format
     * @return localDateTime
     */
    static public LocalDateTime parseString(String dateTimeString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(localDateTimeFormat);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, inputFormatter);
        return localDateTime;
    }

    /**
     * Get a display string in form of "EEEE, dd MMM yyyy HH:mm" from a LocalDateTime.
     * @param localDateTime the datetime to be parsed back to a display string
     * @return displayString in form of "EEEE, dd MMM yyyy HH:mm"
     *                       see <a href="https://www.baeldung.com/java-datetimeformatter">here for more about the format
     */
    static public String getDisplayString(LocalDateTime localDateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(localDateTimeString);
        return localDateTime.format(outputFormatter);
    }

    /**
     * Get the unparsed form of the local datetime (getting the original string back).
     * @param localDateTime the datetime to be parsed back to the original string
     * @return original string in form of "dd/MM/yyyy HHmm"
     *                      see <a href="https://www.baeldung.com/java-datetimeformatter">here for more about the format
     */
    static public String getOriginalString(LocalDateTime localDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(localDateTimeFormat);
        return localDateTime.format(inputFormatter);
    }
}
