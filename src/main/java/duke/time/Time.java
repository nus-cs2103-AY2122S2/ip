package duke.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Returns a custom time object to handle conversions between the different formats of time.
 */
public class Time {

    /**
     * Returns the date in string format.
     *
     * @param date Date in LocalDate format.
     * @return Same date represented in string form.
     */
    public static String convertToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    /**
     * Returns the date in LocalDate format.
     *
     * @param date Date in string format.
     * @return Same date represented in LocalDate form.
     */
    public static LocalDate convertToDate(String date) {
        String[] dates = date.split(" ");
        int year = Integer.parseInt(dates[2]);
        int day = Integer.parseInt(dates[0]);
        switch (dates[1]) {
        case "Jan":
            return LocalDate.of(year, 1, day);
        case "Feb":
            return LocalDate.of(year, 2, day);
        case "Mar":
            return LocalDate.of(year, 3, day);
        case "Apr":
            return LocalDate.of(year, 4, day);
        case "May":
            return LocalDate.of(year, 5, day);
        case "Jun":
            return LocalDate.of(year, 6, day);
        case "July":
            return LocalDate.of(year, 7, day);
        case "Aug":
            return LocalDate.of(year, 8, day);
        case "Sep":
            return LocalDate.of(year, 9, day);
        case "Oct":
            return LocalDate.of(year, 10, day);
        case "Nov":
            return LocalDate.of(year, 11, day);
        default:
            return LocalDate.of(year, 12, day);
        }
    }
}
