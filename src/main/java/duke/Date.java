package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date
 */
public class Date {
    static String formatedDate;

    /**
     * Returns the date as a string in the format dd MMM yyyy, eg: 12 Jan 2022.
     * 
     * @param str the date as a LocalDate object.
     * @return date in String format.
     */
    public static String toString(LocalDate str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu");
        String text = str.format(formatter);
        return text;
    }
    
}
