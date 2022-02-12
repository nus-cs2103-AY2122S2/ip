package duke.command.dateparser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Natural Date Strings to Date and DateTime objects.
 * Examples of Standard Date Strings: 13 Jan 2022, 2022 Feb 23, 2022 23 Feb.
 */
public class NaturalDateParser {
    private static NaturalDateParser instance;

    private final Pattern naturalDatePattern;
    private final Pattern naturalDateTimePattern;

    /**
     * Creates the regex pattern for natural datetime formats.
     */
    private NaturalDateParser() {
        final String dayGroup = DateGroupPatternProvider.getInstance().getDayGroup();
        final String monthGroup = DateGroupPatternProvider.getInstance().getMonthGroup();
        final String yearGroup = DateGroupPatternProvider.getInstance().getYearGroup();
        final String timeGroup = DateGroupPatternProvider.getInstance().getTimeGroup();

        String baseDatePattern = dayGroup + yearGroup + monthGroup;
        String anchoredDatePattern = String.format("^%s.*$", baseDatePattern);
        naturalDatePattern = Pattern.compile(anchoredDatePattern, Pattern.CASE_INSENSITIVE);

        String dateTimePattern = baseDatePattern + timeGroup;
        String anchoredDateTimePattern = String.format("^%s.*$", dateTimePattern);
        naturalDateTimePattern = Pattern.compile(anchoredDateTimePattern, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Creates a {@link Matcher} object for the date regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDatePattern(String input) {
        return this.naturalDatePattern.matcher(input);
    }

    /**
     * Creates a {@link Matcher} object for the date-time regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDateTimePattern(String input) {
        return this.naturalDateTimePattern.matcher(input);
    }

    /**
     * Translates the date component of a regex match for {@link #naturalDatePattern} or
     * {@link #naturalDateTimePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the given patterns.
     * @return A standard formatted string representation of the date.
     * @throws NumberFormatException If either the day or year is not an integer.
     */
    private String parseNaturalDateString(Matcher match) throws NumberFormatException {
        int day = Integer.parseInt(match.group(1));
        int year = Integer.parseInt(match.group(2));
        int month = DateGroupPatternProvider.getInstance().parseNaturalMonth(match.group(3));

        return String.format("%02d/%02d/%04d", day, month, year);
    }

    /**
     * Translates a regex match for {@link #naturalDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the naturalDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseNaturalDate(Matcher match) {
        try {
            return StandardDateParser.getInstance().parseDatePattern(parseNaturalDateString(match));
        } catch (NumberFormatException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Translates a regex match for {@link #naturalDateTimePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the naturalDateTimePattern.
     * @return A LocalDateTime object that represents the supplied date-time, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseNaturalDateTime(Matcher match) {
        try {
            LocalDateTime date = parseNaturalDate(match);
            if (date == null) {
                return null;
            }

            return DateGroupPatternProvider.getInstance().addTimeComponent(match, 4, date);
        } catch (NumberFormatException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Returns the singleton instance of <code>NaturalDateParser</code>.
     *
     * @return The singleton instance of NaturalDateParser.
     */
    public static NaturalDateParser getInstance() {
        if (instance == null) {
            instance = new NaturalDateParser();
        }
        return instance;
    }
}
