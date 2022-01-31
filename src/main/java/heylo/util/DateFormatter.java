package heylo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static String formatDateInLongForm(LocalDate date) {
        try {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (NullPointerException e) {
            return "";
        }
    }
}
