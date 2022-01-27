package butler.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeManager {

    /**
     * Reformats a date and time string that follows a LocalDate and LocalTime format.
     * The default implementation is to reformat a time that follows the ISO_LOCAL_TIME format, and a date that follows
     * the ISO_LOCAL_DATE format.
     * If the date or time cannot be formatted, handles the DateTimeException and returns a null value.
     *
     * @param dateTime String representation of date and time.
     * @return Formatted date and time or null.
     */
    public static String toLocalDateTime(String dateTime) {
        String[] dateThenTime = dateTime.split(" ");
        if (dateThenTime.length != 2) {
            return "";
        }
        String date = dateThenTime[0];
        String time = dateThenTime[1];
        try {
            LocalDate localDate = LocalDate.parse(date);
            LocalTime localTime = LocalTime.parse(time);
            String newLocalDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String newLocalTime = localTime.format(DateTimeFormatter.ofPattern("Hm"));
            if (newLocalTime.length() == 3) {
                newLocalTime = "0" + newLocalTime;
            }
            return newLocalDate + " " + newLocalTime;
        } catch (DateTimeException exception) {
            System.out.println("Input date followed by time");
            System.out.println("Date must be in the format: YY-MM-DD");
            System.out.println("Time must be in the format: hh:mm:ss\n");
        }
        return "";
    }
}
