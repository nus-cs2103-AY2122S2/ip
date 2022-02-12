package duke.command.dateparser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Matcher;

/**
 * Provides Regex patterns as strings for matching various date components.
 */
public class DateGroupPatternProvider {
    private static final String[] MONTH_STRINGS = new String[] {
        "january", "february", "march", "april", "may", "june", "july", "august", "september", "october",
        "november", "december", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov",
        "dec"
    };

    private static DateGroupPatternProvider instance;

    private final String dayGroup;
    private final String monthGroup;
    private final String yearGroup;
    private final String timeGroup;

    /**
     * Builds regex patterns for matching various date components.
     */
    private DateGroupPatternProvider() {
        String allMonths = String.join("|", MONTH_STRINGS);
        dayGroup = "(?=.* (\\d{1,2}) )";
        yearGroup = "(?=.* (\\d{4}) )";
        monthGroup = "(?=.* (" + allMonths + ") )";
        timeGroup = "(?=.* (\\d{1,2}):(\\d{1,2}) )";
    }

    /**
     * Returns the regex pattern for matching day groups.
     *
     * @return The regex pattern for matching day groups.
     */
    String getDayGroup() {
        return this.dayGroup;
    }

    /**
     * Returns the regex pattern for matching month groups.
     *
     * @return The regex pattern for matching month groups.
     */
    String getMonthGroup() {
        return this.monthGroup;
    }

    /**
     * Returns the regex pattern for matching year groups.
     *
     * @return The regex pattern for matching year groups.
     */
    String getYearGroup() {
        return this.yearGroup;
    }

    /**
     * Returns the regex pattern for matching time groups.
     *
     * @return The regex pattern for matching time groups.
     */
    String getTimeGroup() {
        return this.timeGroup;
    }

    /**
     * Adds the time component from a timeGroup regex match to the supplied {@link LocalDateTime} object.
     *
     * @param match The regex match result that includes the timeGroup pattern.
     * @param start The starting index of the timeGroup pattern in the match.
     * @param date The LocalDateTime object to add the time component to.
     * @return A LocalDateTime object with the date from the <code>date</code> argument and time from the
     *         regex match.
     * @throws NumberFormatException If any component of the time is not an integer.
     * @throws DateTimeException If the time is invalid.
     */
    LocalDateTime addTimeComponent(Matcher match, int start, LocalDateTime date)
            throws NumberFormatException, DateTimeException {
        int hour = Integer.parseInt(match.group(start));
        int minute = Integer.parseInt(match.group(start + 1));

        return date.withHour(hour).withMinute(minute);
    }

    /**
     * Translates a month from its natural text representation to its numeric date representation.
     *
     * @param monthText The month to translate in its natural text representation.
     * @return The numeric date representation of the supplied month.
     */
    int parseNaturalMonth(String monthText) {
        String trimmedText = monthText.toLowerCase().trim();
        return (Arrays.asList(MONTH_STRINGS).indexOf(trimmedText) % 12) + 1;
    }

    /**
     * Returns the singleton instance of <code>DateGroupPatternProvider</code>.
     *
     * @return The singleton instance of DateGroupPatternProvider.
     */
    static DateGroupPatternProvider getInstance() {
        if (instance == null) {
            instance = new DateGroupPatternProvider();
        }
        return instance;
    }
}
