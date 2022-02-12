package duke.command.dateparser;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Day Strings to Date and DateTime objects.
 * Examples of Day Strings: next monday, next mon.
 */
public class DayDateParser {
    private static final String[] DAY_STRINGS = new String[] {
        "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday",
        "mon", "tue", "wed", "thu", "fri", "sat", "sun"
    };

    private static final DayOfWeek[] DAY_MAPPING = new DayOfWeek[] {
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY
    };

    private static DayDateParser instance;
    private final Pattern dayPattern;
    private final Pattern dayTimePattern;

    /**
     * Creates the regex pattern for natural next x-day formats.
     */
    private DayDateParser() {
        final String allDays = String.join("|", DAY_STRINGS);
        final String dayGroup = "(?=.* (" + allDays + ") )";
        dayPattern = Pattern.compile(String.format("^%s.*$", dayGroup), Pattern.CASE_INSENSITIVE);
        final String timeGroup = DateGroupPatternProvider.getInstance().getTimeGroup();
        dayTimePattern = Pattern.compile(String.format("^%s" + timeGroup + ".*$", dayGroup),
                Pattern.CASE_INSENSITIVE);
    }

    /**
     * Creates a {@link Matcher} object for the date regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDatePattern(String input) {
        return this.dayPattern.matcher(input);
    }

    /**
     * Creates a {@link Matcher} object for the date-time regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDateTimePattern(String input) {
        return this.dayTimePattern.matcher(input);
    }

    /**
     * Translates a day-of-week from its natural text representation to its numeric date representation.
     *
     * @param dayText The day-of-week to translate in its natural text representation.
     * @return The DayOfWeek representation of the supplied day-of-week.
     */
    private DayOfWeek parseNaturalDay(String dayText) {
        String trimmedText = dayText.toLowerCase().trim();
        return DAY_MAPPING[(Arrays.asList(DAY_STRINGS).indexOf(trimmedText) % 7)];
    }

    /**
     * Translates a regex match for {@link #dayPattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the dayPattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseDayDate(Matcher match) {
        DayOfWeek dayOfWeek = parseNaturalDay(match.group(1));
        LocalDateTime today = LocalDate.now().atStartOfDay();
        return today.with(TemporalAdjusters.next(dayOfWeek));
    }

    /**
     * Translates a regex match for {@link #dayTimePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the dayTimePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseDayDateTime(Matcher match) {
        try {
            LocalDateTime date = parseDayDate(match);
            return DateGroupPatternProvider.getInstance().addTimeComponent(match, 2, date);
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Returns the singleton instance of <code>DayDateParser</code>.
     *
     * @return The singleton instance of DayDateParser.
     */
    public static DayDateParser getInstance() {
        if (instance == null) {
            instance = new DayDateParser();
        }
        return instance;
    }
}
