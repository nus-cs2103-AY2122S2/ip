package heylo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Formats dates.
 */
public class DateFormatter {
    /**
     * Formats the given date in MMM d yyyy format.
     *
     * @param date LocalDate date.
     * @return String date.
     */
    public static String formatDateInLongForm(LocalDate date) {
        try {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
            return "";
        }
    }
}
