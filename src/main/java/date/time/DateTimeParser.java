package date.time;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;

/**
 * This class encapsulates a Date Time Parser. It contains methods to parse inputs into a
 * LocalDateTime object, as well as a checker for the format.
 *
 * @author Ong Han Yang
 */
public final class DateTimeParser {
    /**
     * Parses a string input in the form yyyy-mm-dd hh:mm, where dd is the day, mm is the month,
     * yyyy is the year and hh:mm is the 24 hour time, to produce a LocalDateTime object
     * with the provided date and time.
     *
     * @param input the string input in the form yyyy-mm-dd xx:xx .
     * @return the LocalDateTime corresponding to the input.
     */
    public static LocalDateTime parse(String input) {
        String[] split = input.split(" ", 2);
        int[] date = Arrays.stream(split[0]
                .split("-", 3))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] time = Arrays.stream(split[1]
                .split(":", 2))
                .mapToInt(Integer::parseInt)
                .toArray();
        return LocalDateTime.of(
                date[0],
                date[1],
                date[2],
                time[0],
                time[1]);
    }

    /**
     * Checks if a given string input has a valid format to be converted into
     * a LocalDateTime using the DateTimeParser::parse method.
     *
     * @param input The string to be checked if it follows yyyy-mm-dd hh:mm .
     * @return a boolean value indicating if the format is valid.
     */
    public static boolean checkValidFormat(String input) {
        if (input.length() != 16) {
            return false;
        }
        // check for separators and numbers
        // 0123456789012345
        // yyyy-mm-dd xx:xx
        for (int i = 0; i < input.length(); i++) {
            if (i == 4 || i == 7) {
                if (input.charAt(i) != '-') {
                    return false;
                }
            } else if (i == 10) {
                if (input.charAt(10) != ' ') {
                    return false;
                }
            } else if (i == 13) {
                if (input.charAt(13) != ':') {
                    return false;
                }
            } else { // for non divider indexes
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
        }

        // check valid numbers for months and days
        int monthNum = Integer.parseInt(input.substring(5, 7));
        if (monthNum < 1 || monthNum > 12) {
            return false;
        }
        Month month = Month.of(monthNum);
        Year year = Year.of(Integer.parseInt(input.substring(0, 4)));
        int dayNum = Integer.parseInt(input.substring(8, 10));
        if (dayNum < 1) {
            return false;
        }
        if (!year.isLeap() && monthNum == 2) { // leap year
            if (dayNum > month.minLength()) {
                return false;
            }
        } else {
            if (dayNum > month.maxLength()) {
                return false;
            }
        }

        // check valid numbers for hours and min
        int hours = Integer.parseInt(input.substring(11, 13));
        int mins = Integer.parseInt((input.substring(14, 16)));
        if (hours > 23 || mins > 59) {
            return false;
        }
        return true;
    }
}
