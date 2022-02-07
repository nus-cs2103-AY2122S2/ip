package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateParser {
    /**
     * Parses date string entered as user input and returns a <code>LocalDate</code>
     * @param date date found in user input in String form
     * @return <code>LocalDate</code> corresponding to the user input
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Parses a <code>LocalDate</code> and returns a string representing the <code>LocalDate</code>
     * @param date <code>LocalDate</code> object
     * @return String corresponding to the <code>LocalDate</code>
     */
    public static String dateToString(LocalDate date) {
        assert date != null : "LocalDate object is not initialized correctly.";
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
