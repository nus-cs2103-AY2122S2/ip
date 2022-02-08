package duke.command;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing services for converting Strings to Date and DateTime objects.
 */
class NaturalDateParser {
    private static final String[] DAY_STRINGS = new String[] {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    private static final String[] DAY_SHORT_STRINGS = new String[] {
        "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
    };

    private static final String[] MONTH_STRINGS = new String[] {
        "january", "february", "march", "april", "may", "june", "july", "august", "september", "october",
        "november", "december", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov",
        "dec"
    };

    private static final String KEYWORD_RELATIVE_YEAR = "next year";
    private static final String KEYWORD_RELATIVE_MONTH = "next month";
    private static final String KEYWORD_RELATIVE_DAY = "tomorrow";
    private static final String KEYWORD_POSITIONAL_DAY = "today";

    private static final String DEFAULT_TIME = " 00:00";
    private static final String FORMAT_DEFAULT_DATETIME = "dd/MM/yyyy HH:mm";
    private static final String FORMAT_DEFAULT_DATE = "dd/MM/yyyy";

    private static NaturalDateParser instance;

    private final Pattern standardDatePattern;
    private final Pattern standardDateTimePattern;
    private final Pattern naturalDatePattern;
    private final Pattern naturalDateTimePattern;
    private final Pattern relativeDatePattern;
    private final Pattern relativeDateTimePattern;

    private NaturalDateParser() {
        standardDatePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4})");
        standardDateTimePattern = Pattern.compile("(\\d{2})[/-](\\d{2})[/-](\\d{4}) (\\d{2})[-:](\\d{2})");

        // Build Natural Patterns
        String allMonths = String.join("|", MONTH_STRINGS);
        String dayGroup = "(?=.* (\\d{1,2}) )";
        String yearGroup = "(?=.* (\\d{4}) )";
        String monthGroup = "(?=.* (" + allMonths + ") )";
        String timeGroup = "(?=.* (\\d{1,2}):(\\d{1,2}) )";

        String baseDatePattern = dayGroup + yearGroup + monthGroup;
        String anchoredDatePattern = String.format("^%s.*$", baseDatePattern);
        naturalDatePattern = Pattern.compile(anchoredDatePattern, Pattern.CASE_INSENSITIVE);

        String dateTimePattern = baseDatePattern + timeGroup;
        String anchoredDateTimePattern = String.format("^%s.*$", dateTimePattern);
        naturalDateTimePattern = Pattern.compile(anchoredDateTimePattern, Pattern.CASE_INSENSITIVE);

        // Build Relative Patterns
        String relativeDayPattern = "(?=.* (" + KEYWORD_RELATIVE_DAY + "))";
        String relativeMonthPattern = dayGroup + "(?=.* (" + KEYWORD_RELATIVE_MONTH + "))";
        String relativeYearPattern = dayGroup + monthGroup + "(?=.* (" + KEYWORD_RELATIVE_YEAR + "))";
        String combinedRelativeDatePattern = String.format("^(?:%s|%s|%s).*$", relativeDayPattern,
                relativeMonthPattern, relativeYearPattern);
        String combinedRelativeDateTimePattern = String.format("^(?:%s|%s|%s)%s.*$", relativeDayPattern,
                relativeMonthPattern, relativeYearPattern, timeGroup);
        relativeDatePattern = Pattern.compile(combinedRelativeDatePattern, Pattern.CASE_INSENSITIVE);
        relativeDateTimePattern = Pattern.compile(combinedRelativeDateTimePattern, Pattern.CASE_INSENSITIVE);
    }

    public static NaturalDateParser getInstance() {
        if (instance == null) {
            instance = new NaturalDateParser();
        }
        return instance;
    }

    LocalDateTime parseDate(String input) {
        if (input.equalsIgnoreCase(KEYWORD_POSITIONAL_DAY)) {
            return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        }

        Matcher standardMatcher = standardDatePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseStandardDate(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
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

    LocalDateTime parseDateTime(String input) {
        if (input.equalsIgnoreCase(KEYWORD_POSITIONAL_DAY)) {
            return LocalDateTime.now();
        }

        Matcher standardMatcher = standardDateTimePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseStandardDateTime(standardMatcher);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
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
     * Translates a patterned string to a {@link LocalDateTime} object based on the given format.
     *
     * @param dateString Date-time string to process.
     * @param pattern Pattern format that the date-time string is in.
     * @return A {@link LocalDateTime} that represents the supplied date-time.
     */
    private LocalDateTime parseDateTimePattern(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private LocalDateTime parseDateTimePattern(String dateString) {
        return parseDateTimePattern(dateString, FORMAT_DEFAULT_DATETIME);
    }

    private LocalDateTime parseDatePattern(String dateString) {
        return parseDateTimePattern(dateString + DEFAULT_TIME, FORMAT_DEFAULT_DATETIME);
    }

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

    private int parseNaturalMonth(String monthText) {
        String trimmedText = monthText.toLowerCase().trim();
        return (Arrays.asList(MONTH_STRINGS).indexOf(trimmedText) % 12) + 1;
    }

    private String parseNaturalDateString(Matcher match) throws NumberFormatException {
        int day = Integer.parseInt(match.group(1));
        int year = Integer.parseInt(match.group(2));
        int month = parseNaturalMonth(match.group(3));

        return String.format("%02d/%02d/%04d", day, month, year);
    }

    private LocalDateTime addTimeComponent(Matcher match, int start, LocalDateTime date)
            throws NumberFormatException, DateTimeException {
        int hour = Integer.parseInt(match.group(start));
        int minute = Integer.parseInt(match.group(start + 1));

        return date.withHour(hour).withMinute(minute);
    }

    private LocalDateTime parseNaturalDate(Matcher match) {
        try {
            return parseDatePattern(parseNaturalDateString(match));
        } catch (NumberFormatException | DateTimeException ex) {
            return null;
        }
    }

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

    private LocalDateTime parseRelativeDate(Matcher match) {
        try {
            if (match.group(1) != null) {
                // Tomorrow Case
                return LocalDateTime.now().plusDays(1);
            } else if (match.group(3) != null) {
                // Next Month Case
                int day = Integer.parseInt(match.group(2));
                return LocalDateTime.now().plusMonths(1).withDayOfMonth(day);
            } else if (match.group(6) != null) {
                // Next Year Case
                int day = Integer.parseInt(match.group(4));
                int month = parseNaturalMonth(match.group(5));
                return LocalDateTime.now().plusYears(1).withMonth(month).withDayOfMonth(day);
            }
            return null;
        } catch (NumberFormatException | NullPointerException | DateTimeException ex) {
            return null;
        }
    }

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
