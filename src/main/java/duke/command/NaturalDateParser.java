package duke.command;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Strings to Date and DateTime objects.
 */
class NaturalDateParser {
    private static final String[] DAY_STRINGS = new String[] {
        "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday",
        "mon", "tue", "wed", "thu", "fri", "sat", "sun"
    };

    private static final String[] MONTH_STRINGS = new String[] {
        "january", "february", "march", "april", "may", "june", "july", "august", "september", "october",
        "november", "december", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov",
        "dec"
    };

    final DayOfWeek[] dayMapping = new DayOfWeek[] {
        DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY,
        DayOfWeek.THURSDAY,
        DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY,
        DayOfWeek.SUNDAY
    };

    private static final String KEYWORD_RELATIVE_YEAR = "next year";
    private static final String KEYWORD_RELATIVE_MONTH = "next month";
    private static final String KEYWORD_RELATIVE_DAY = "tomorrow";
    private static final String KEYWORD_POSITIONAL_DAY = "today";
    private static final String KEYWORD_POSITIONAL_MONTH = "this month";
    private static final String KEYWORD_POSITIONAL_YEAR = "this year";

    private static final String DEFAULT_TIME = " 00:00";
    private static final String FORMAT_DEFAULT_DATETIME = "dd/MM/yyyy HH:mm";

    private static NaturalDateParser instance;

    private Pattern standardDatePattern;
    private Pattern standardDateTimePattern;
    private Pattern dayPattern;
    private Pattern dayTimePattern;
    private Pattern naturalDatePattern;
    private Pattern naturalDateTimePattern;
    private Pattern relativeDatePattern;
    private Pattern relativeDateTimePattern;

    /**
     * Creates the singleton instance of NaturalDateParser.
     */
    private NaturalDateParser() {
        // Create natural pattern matchers.
        String allMonths = String.join("|", MONTH_STRINGS);
        String dayGroup = "(?=.* (\\d{1,2}) )";
        String yearGroup = "(?=.* (\\d{4}) )";
        String monthGroup = "(?=.* (" + allMonths + ") )";
        String timeGroup = "(?=.* (\\d{1,2}):(\\d{1,2}) )";

        buildStandardPatterns();
        buildDayPatterns(timeGroup);
        buildNaturalPatterns(dayGroup, monthGroup, yearGroup, timeGroup);
        buildRelativePatterns(dayGroup, monthGroup, timeGroup);
    }

    /**
     * Get the singleton instance of <code>NaturalDateParser</code>.
     *
     * @return The singleton instance of NaturalDateParser.
     */
    public static NaturalDateParser getInstance() {
        if (instance == null) {
            instance = new NaturalDateParser();
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
    LocalDateTime parseDate(String input) {
        Matcher standardMatcher = standardDatePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseStandardDate(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher dayMatcher = dayPattern.matcher(paddedInput);
        if (dayMatcher.matches()) {
            return parseDayDate(dayMatcher);
        }

        Matcher naturalMatcher = naturalDatePattern.matcher(paddedInput);
        if (naturalMatcher.matches()) {
            return parseNaturalDate(naturalMatcher);
        }

        Matcher relativeMatcher = relativeDatePattern.matcher(paddedInput);
        if (relativeMatcher.matches()) {
            return parseRelativeDate(relativeMatcher);
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
    LocalDateTime parseDateTime(String input) {
        Matcher standardMatcher = standardDateTimePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseStandardDateTime(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher dayMatcher = dayTimePattern.matcher(paddedInput);
        if (dayMatcher.matches()) {
            return parseDayTimeDate(dayMatcher);
        }

        Matcher naturalMatcher = naturalDateTimePattern.matcher(paddedInput);
        if (naturalMatcher.matches()) {
            return parseNaturalDateTime(naturalMatcher);
        }

        Matcher relativeMatcher = relativeDateTimePattern.matcher(paddedInput);
        if (relativeMatcher.matches()) {
            return parseRelativeDateTime(relativeMatcher);
        }

        return null;
    }

    /**
     * Creates the regex pattern for the standard rigid datetime format.
     */
    private void buildStandardPatterns() {
        standardDatePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4})");
        standardDateTimePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4}) "
                + "(\\d{1,2})[-:](\\d{1,2})");
    }

    /**
     * Creates the regex pattern for natural next x-day formats.
     *
     * @param timeGroup The regex capture group for time.
     */
    private void buildDayPatterns(String timeGroup) {
        String allDays = String.join("|", DAY_STRINGS);
        String dayGroup = "(?=.* (" + allDays + ") )";
        dayPattern = Pattern.compile(String.format("^%s.*$", dayGroup), Pattern.CASE_INSENSITIVE);
        dayTimePattern = Pattern.compile(String.format("^%s" + timeGroup + ".*$", dayGroup),
                Pattern.CASE_INSENSITIVE);
    }

    /**
     * Creates the regex pattern for natural datetime formats.
     *
     * @param dayGroup The regex capture group for day.
     * @param monthGroup The regex capture group for month.
     * @param yearGroup The regex capture group for year.
     * @param timeGroup The regex capture group for time.
     */
    private void buildNaturalPatterns(String dayGroup, String monthGroup, String yearGroup,
                                      String timeGroup) {
        String baseDatePattern = dayGroup + yearGroup + monthGroup;
        String anchoredDatePattern = String.format("^%s.*$", baseDatePattern);
        naturalDatePattern = Pattern.compile(anchoredDatePattern, Pattern.CASE_INSENSITIVE);

        String dateTimePattern = baseDatePattern + timeGroup;
        String anchoredDateTimePattern = String.format("^%s.*$", dateTimePattern);
        naturalDateTimePattern = Pattern.compile(anchoredDateTimePattern, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Creates the regex pattern for relative datetime formats.
     *
     * @param dayGroup The regex capture group for day.
     * @param monthGroup The regex capture group for month.
     * @param timeGroup The regex capture group for time.
     */
    private void buildRelativePatterns(String dayGroup, String monthGroup, String timeGroup) {
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
     * Translates a patterned string to a {@link LocalDateTime} object based on the given format.
     *
     * @param dateString Date-time string to process.
     * @param pattern Pattern format that the date-time string is in.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if supplied String
     *                                 is invalid.
     */
    private LocalDateTime parseDateTimePattern(String dateString, String pattern) {
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
    private LocalDateTime parseDateTimePattern(String dateString) {
        return parseDateTimePattern(dateString, FORMAT_DEFAULT_DATETIME);
    }

    /**
     * Translates a date patterned string to a {@link LocalDateTime} object based on the default format.
     *
     * @param dateString Date string to process.
     * @return A {@link LocalDateTime} that represents the supplied date-time, or null if supplied String
     *                                 is invalid.
     */
    private LocalDateTime parseDatePattern(String dateString) {
        return parseDateTimePattern(dateString + DEFAULT_TIME, FORMAT_DEFAULT_DATETIME);
    }

    /**
     * Translates a regex match for {@link #standardDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the standardDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    private LocalDateTime parseStandardDate(Matcher match) {
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
    private LocalDateTime parseStandardDateTime(Matcher match) {
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
     * Translates a day-of-week from its natural text representation to its numeric date representation.
     *
     * @param dayText The day-of-week to translate in its natural text representation.
     * @return The DayOfWeek representation of the supplied day-of-week.
     */
    private DayOfWeek parseNaturalDay(String dayText) {
        String trimmedText = dayText.toLowerCase().trim();
        return dayMapping[(Arrays.asList(DAY_STRINGS).indexOf(trimmedText) % 7)];
    }

    /**
     * Translates a regex match for {@link #dayPattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the dayPattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    private LocalDateTime parseDayDate(Matcher match) {
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
    private LocalDateTime parseDayTimeDate(Matcher match) {
        try {
            LocalDateTime date = parseDayDate(match);
            return addTimeComponent(match, 2, date);
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Translates a month from its natural text representation to its numeric date representation.
     *
     * @param monthText The month to translate in its natural text representation.
     * @return The numeric date representation of the supplied month.
     */
    private int parseNaturalMonth(String monthText) {
        String trimmedText = monthText.toLowerCase().trim();
        return (Arrays.asList(MONTH_STRINGS).indexOf(trimmedText) % 12) + 1;
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
        int month = parseNaturalMonth(match.group(3));

        return String.format("%02d/%02d/%04d", day, month, year);
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
    private LocalDateTime addTimeComponent(Matcher match, int start, LocalDateTime date)
            throws NumberFormatException, DateTimeException {
        int hour = Integer.parseInt(match.group(start));
        int minute = Integer.parseInt(match.group(start + 1));

        return date.withHour(hour).withMinute(minute);
    }

    /**
     * Translates a regex match for {@link #naturalDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the naturalDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    private LocalDateTime parseNaturalDate(Matcher match) {
        try {
            return parseDatePattern(parseNaturalDateString(match));
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
    private LocalDateTime parseNaturalDateTime(Matcher match) {
        try {
            LocalDateTime date = parseNaturalDate(match);
            if (date == null) {
                return null;
            }

            return addTimeComponent(match, 4, date);
        } catch (NumberFormatException | DateTimeException ex) {
            return null;
        }
    }

    /**
     * Translates a regex match for {@link #relativeDatePattern} into a {@link LocalDateTime} object.
     *
     * @param match The regex match result for the relativeDatePattern.
     * @return A LocalDateTime object that represents the supplied date, or null if supplied String
     *         is invalid.
     */
    private LocalDateTime parseRelativeDate(Matcher match) {
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
        int month = parseNaturalMonth(match.group(5));
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
    private LocalDateTime parseRelativeDateTime(Matcher match) {
        try {
            LocalDateTime date = parseRelativeDate(match);
            if (date == null) {
                return null;
            }

            return addTimeComponent(match, 7, date);
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }
}
