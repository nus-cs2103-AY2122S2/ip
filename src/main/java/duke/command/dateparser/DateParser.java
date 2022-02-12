package duke.command.dateparser;

import java.time.LocalDateTime;
import java.util.regex.Matcher;

/**
 * Provides parsing services for converting Strings to Date and DateTime objects.
 */
public class DateParser {
    private static DateParser instance;

    private DateParser() {

    }

    /**
     * Returns the singleton instance of <code>DateParser</code>.
     *
     * @return The singleton instance of DateParser.
     */
    public static DateParser getInstance() {
        if (instance == null) {
            instance = new DateParser();
        }
        return instance;
    }

    /**
     * Translates a date string to a {@link LocalDateTime} object.
     *
     * @param input Date string to process.
     * @return A {@link LocalDateTime} that represents the supplied date at 00:00 hours, or null if
     *                                 the supplied date string is invalid.
     */
    public LocalDateTime parseDate(String input) {
        Matcher standardMatcher = StandardDateParser.getInstance().matchDatePattern(input);
        if (standardMatcher.matches()) {
            return StandardDateParser.getInstance().parseStandardDate(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher dayMatcher = DayDateParser.getInstance().matchDatePattern(paddedInput);
        if (dayMatcher.matches()) {
            return DayDateParser.getInstance().parseDayDate(dayMatcher);
        }

        Matcher naturalMatcher = NaturalDateParser.getInstance().matchDatePattern(paddedInput);
        if (naturalMatcher.matches()) {
            return NaturalDateParser.getInstance().parseNaturalDate(naturalMatcher);
        }

        Matcher relativeMatcher = RelativeDateParser.getInstance().matchDatePattern(paddedInput);
        if (relativeMatcher.matches()) {
            return RelativeDateParser.getInstance().parseRelativeDate(relativeMatcher);
        }

        return null;
    }

    /**
     * Translates a date-time string to a {@link LocalDateTime} object.
     *
     * @param input Date-time string to process.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if the supplied
     *                                 date-time string is invalid.
     */
    public LocalDateTime parseDateTime(String input) {
        Matcher standardMatcher = StandardDateParser.getInstance().matchDateTimePattern(input);
        if (standardMatcher.matches()) {
            return StandardDateParser.getInstance().parseStandardDateTime(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher dayMatcher = DayDateParser.getInstance().matchDateTimePattern(paddedInput);
        if (dayMatcher.matches()) {
            return DayDateParser.getInstance().parseDayDateTime(dayMatcher);
        }

        Matcher naturalMatcher = NaturalDateParser.getInstance().matchDateTimePattern(paddedInput);
        if (naturalMatcher.matches()) {
            return NaturalDateParser.getInstance().parseNaturalDateTime(naturalMatcher);
        }

        Matcher relativeMatcher = RelativeDateParser.getInstance().matchDateTimePattern(paddedInput);
        if (relativeMatcher.matches()) {
            return RelativeDateParser.getInstance().parseRelativeDateTime(relativeMatcher);
        }

        return null;
    }
}
