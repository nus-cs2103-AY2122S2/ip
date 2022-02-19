package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date
 */
public class Date {
    /**
     * Returns the date as a string in the format dd MMM yyyy, eg: 12 Jan 2022.
     * 
     * @param date the date as a LocalDate object.
     * @return date in String format.
     */
    public static String toString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        String dateString = date.format(formatter);
        return dateString;
    }
}
