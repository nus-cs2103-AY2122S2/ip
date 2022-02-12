package duke.command.dateparser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Relative Date Strings to Date and DateTime objects.
 * Examples of Relative Date Strings: 13 next month, 5 Sep next year, 10 this month.
 */
public class RelativeDateParser {
    private static final String KEYWORD_RELATIVE_YEAR = "next year";
    private static final String KEYWORD_RELATIVE_MONTH = "next month";
    private static final String KEYWORD_RELATIVE_DAY = "tomorrow";
    private static final String KEYWORD_POSITIONAL_DAY = "today";
    private static final String KEYWORD_POSITIONAL_MONTH = "this month";
    private static final String KEYWORD_POSITIONAL_YEAR = "this year";

    private static RelativeDateParser instance;

    private final Pattern relativeDatePattern;
    private final Pattern relativeDateTimePattern;

    /**
     * Creates the regex pattern for relative datetime formats.
     */
    private RelativeDateParser() {
        final String dayGroup = DateGroupPatternProvider.getInstance().getDayGroup();
        final String monthGroup = DateGroupPatternProvider.getInstance().getMonthGroup();
        final String timeGroup = DateGroupPatternProvider.getInstance().getTimeGroup();

        String relativeDayPattern = "(?=.* (" + KEYWORD_RELATIVE_DAY + "|" + KEYWORD_POSITIONAL_DAY + "))";
        String relativeMonthPattern = dayGroup + "(?=.* (" + KEYWORD_RELATIVE_MONTH + "|"
                + KEYWORD_POSITIONAL_MONTH + "))";
        String relativeYearPattern = dayGroup + monthGroup + "(?=.* (" + KEYWORD_RELATIVE_YEAR + "|"
                + KEYWORD_POSITIONAL_YEAR + "))";
        String combinedRelativeDatePattern = String.format("^(?:%s|%s|%s).*$", relativeDayPattern,
                relativeMonthPattern, relativeYearPattern);
        String combinedRelativeDateTimePattern = String.format("^(?:%s|%s|%s)%s.*$", relativeDayPattern,
                relativeMonthPattern, relativeYearPattern, timeGroup);
        relativeDatePattern = Pattern.compile(combinedRelativeDatePattern, Pattern.CASE_INSENSITIVE);
        relativeDateTimePattern = Pattern.compile(combinedRelativeDateTimePattern, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Creates a {@link Matcher} object for the date regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDatePattern(String input) {
        return this.relativeDatePattern.matcher(input);
    }

    /**
     * Creates a {@link Matcher} object for the date-time regex pattern used for this parser.
     *
     * @param input The input string to be matched against.
     * @return A <code>Matcher</code> object containing the Regex results.
     */
    Matcher matchDateTimePattern(String input) {
        return this.relativeDateTimePattern.matcher(input);
    }

    /**
     * Translates a regex match for {@link #relativeDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the relativeDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseRelativeDate(Matcher match) {
        final LocalDateTime now = LocalDate.now().atStartOfDay();

        try {
            if (match.group(1) != null) {
                // Tomorrow or Today Case
                return parseRelativeDay(now, match);
            } else if (match.group(3) != null) {
                // Next Month or This Month Case
                return parseRelativeMonth(now, match);
            } else if (match.group(6) != null) {
                // Next Year Case
                return parseRelativeYear(now, match);
            }
            return null;
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Translates a regex match for a day-relative date string for {@link #relativeDatePattern} or
     * {@link #relativeDateTimePattern}.
     *
     * @param now A LocalDateTime object representing the current date.
     * @param match The regex match result for the day-relative date string.
     * @return A LocalDateTime object representing the adjusted date.
     */
    private LocalDateTime parseRelativeDay(LocalDateTime now, Matcher match) {
        if (match.group(1).equalsIgnoreCase(KEYWORD_RELATIVE_DAY)) {
            return now.plusDays(1);
        } else if (match.group(1).equalsIgnoreCase(KEYWORD_POSITIONAL_DAY)) {
            return now;
        }
        return null;
    }

    /**
     * Translates a regex match for a month-relative date string for {@link #relativeDatePattern} or
     * {@link #relativeDateTimePattern}.
     *
     * @param now A LocalDateTime object representing the current date.
     * @param match The regex match result for the month-relative date string.
     * @return A LocalDateTime object representing the adjusted date.
     * @throws NumberFormatException If day is not an integer.
     */
    private LocalDateTime parseRelativeMonth(LocalDateTime now, Matcher match) throws NumberFormatException {
        int day = Integer.parseInt(match.group(2));
        String anchorWord = match.group(3).toLowerCase();

        if (anchorWord.equals(KEYWORD_RELATIVE_MONTH)) {
            return now.plusMonths(1).withDayOfMonth(day);
        } else if (anchorWord.equals(KEYWORD_POSITIONAL_MONTH)) {
            return now.withDayOfMonth(day);
        }
        return null;
    }

    /**
     * Translates a regex match for a year-relative date string for {@link #relativeDatePattern} or
     * {@link #relativeDateTimePattern}.
     *
     * @param now A LocalDateTime object representing the current date.
     * @param match The regex match result for the year-relative date string.
     * @return A LocalDateTime object representing the adjusted date.
     * @throws NumberFormatException If day is not an integer.
     */
    private LocalDateTime parseRelativeYear(LocalDateTime now, Matcher match) throws NumberFormatException {
        int day = Integer.parseInt(match.group(4));
        int month = DateGroupPatternProvider.getInstance().parseNaturalMonth(match.group(5));
        String anchorWord = match.group(6).toLowerCase();

        if (anchorWord.equals(KEYWORD_RELATIVE_YEAR)) {
            return now.plusYears(1).withMonth(month).withDayOfMonth(day);
        } else if (anchorWord.equals(KEYWORD_POSITIONAL_YEAR)) {
            return now.withMonth(month).withDayOfMonth(day);
        }
        return null;
    }

    /**
     * Translates a regex match for {@link #relativeDateTimePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the relativeDateTimePattern.
     * @return A LocalDateTime object that represents the supplied date-time, or null if supplied String
     *         is invalid.
     */
    LocalDateTime parseRelativeDateTime(Matcher match) {
        try {
            LocalDateTime date = parseRelativeDate(match);
            if (date == null) {
                return null;
            }

            return DateGroupPatternProvider.getInstance().addTimeComponent(match, 7, date);
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Returns the singleton instance of <code>RelativeDateParser</code>.
     *
     * @return The singleton instance of RelativeDateParser.
     */
    static RelativeDateParser getInstance() {
        if (instance == null) {
            instance = new RelativeDateParser();
        }
        return instance;
    }
}
