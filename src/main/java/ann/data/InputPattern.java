package ann.data;

import java.util.regex.Pattern;

/**
 * Contains Patterns and methods related to the format of user input.
 *
 * @author Hong Anh
 * @version 0.1
 */
public final class InputPattern {
    /** Represents the expected format for date and time input */
    private static final Pattern DATE_TIME_FORMAT = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");

    /**
     * Returns true if the given String has the expected format for date and time.
     *
     * @param dateTime a String.
     * @return a boolean which indicates whether dateTime matches the DATE_TIME_FORMAT Pattern.
     */
    public static boolean isValidDateTimeString(String dateTime) {
        return DATE_TIME_FORMAT.matcher(dateTime).matches();
    }
}
