package duke.command.dateparser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Standard Date Strings to Date and DateTime objects.
 * Examples of Standard Date Strings: 12/12/2022 11:00, 23-02-2034 12:34.
 */
public class StandardDateParser {
    private static final String DEFAULT_TIME = " 00:00";
    private static final String FORMAT_DEFAULT_DATETIME = "dd/MM/yyyy HH:mm";

    private static StandardDateParser instance;

    private final Pattern standardDatePattern;
    private final Pattern standardDateTimePattern;

    /**
     * Creates the regex pattern for the standard rigid datetime format.
     */
    private StandardDateParser() {
        standardDatePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4})");
        standardDateTimePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4}) "
                + "(\\d{1,2})[-:](\\d{1,2})");
    }

    /**
     * Creates a {@link Matcher} object for the date regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDatePattern(String input) {
        return this.standardDatePattern.matcher(input);
    }

    /**
     * Creates a {@link Matcher} object for the date-time regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDateTimePattern(String input) {
        return this.standardDateTimePattern.matcher(input);
    }

    /**
     * Translates a patterned string to a {@link LocalDateTime} object based on the given format.
     *
     * @param dateString Date-time string to process.
     * @param pattern Pattern format that the date-time string is in.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if supplied String
     *                                 is invalid.
     */
    LocalDateTime parseDateTimePattern(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * Translates a date-time patterned string to a {@link LocalDateTime} object based on the default format.
     *
     * @param dateString Date-time string to process.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if supplied String
     *                                 is invalid.
     */
    LocalDateTime parseDateTimePattern(String dateString) {
        return parseDateTimePattern(dateString, FORMAT_DEFAULT_DATETIME);
    }

    /**
     * Translates a date patterned string to a {@link LocalDateTime} object based on the default format.
     *
     * @param dateString Date string to process.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if supplied String
     *                                 is invalid.
     */
    LocalDateTime parseDatePattern(String dateString) {
        return parseDateTimePattern(dateString + DEFAULT_TIME, FORMAT_DEFAULT_DATETIME);
    }

    /**
     * Translates a regex match for {@link #standardDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the standardDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseStandardDate(Matcher match) {
        try {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            int year = Integer.parseInt(match.group(3));

            return parseDatePattern(String.format("%02d/%02d/%04d", day, month, year));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Translates a regex match for {@link #standardDateTimePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the standardDateTimePattern.
     * @return A LocalDateTime object that represents the supplied date-time, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseStandardDateTime(Matcher match) {
        try {
            int day = Integer.parseInt(match.group(1));
            int month = Integer.parseInt(match.group(2));
            int year = Integer.parseInt(match.group(3));
            int hour = Integer.parseInt(match.group(4));
            int minute = Integer.parseInt(match.group(5));

            return parseDateTimePattern(String.format("%02d/%02d/%04d %02d:%02d", day, month, year,
                    hour, minute));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Returns the singleton instance of <code>StandardDateParser</code>.
     *
     * @return The singleton instance of StandardDateParser.
     */
    static StandardDateParser getInstance() {
        if (instance == null) {
            instance = new StandardDateParser();
        }
        return instance;
    }
}
