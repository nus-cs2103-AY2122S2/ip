package duke.command;

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

    private static final String DEFAULT_TIME = " 00:00";
    private static final String FORMAT_DEFAULT_DATETIME = "dd/MM/yyyy HH:mm";
    private static final String FORMAT_DEFAULT_DATE = "dd/MM/yyyy";

    private static NaturalDateParser instance;

    private final Pattern standardDatePattern;
    private final Pattern standardDateTimePattern;
    private final Pattern naturalDatePattern;
    private final Pattern naturalDateTimePattern;

    private NaturalDateParser() {
        standardDatePattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})");
        standardDateTimePattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2})");

        // Build Natural Patterns
        String allMonths = Arrays.stream(MONTH_STRINGS).reduce("", (x, y) -> x + "|" + y);
        // Trim leading pipe as side effect of reduce operation
        allMonths = allMonths.substring(1);

        String baseDatePattern = "(?=.* (\\d{1,2}) )(?=.* (\\d{4}) )(?=.* (" + allMonths + ") )";
        String anchoredDatePattern = String.format("^%s.*$", baseDatePattern);
        naturalDatePattern = Pattern.compile(anchoredDatePattern, Pattern.CASE_INSENSITIVE);

        String dateTimePattern = baseDatePattern + "(?=.* (\\d{1,2}):(\\d{1,2}) )";
        String anchoredDateTimePattern = String.format("^%s.*$", dateTimePattern);
        naturalDateTimePattern = Pattern.compile(anchoredDateTimePattern, Pattern.CASE_INSENSITIVE);
    }

    public static NaturalDateParser getInstance() {
        if (instance == null) {
            instance = new NaturalDateParser();
        }
        return instance;
    }

    LocalDateTime parseDate(String input) {
        Matcher standardMatcher = standardDatePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseDateTime(standardMatcher.group(1) + DEFAULT_TIME, FORMAT_DEFAULT_DATETIME);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher naturalMatcher = naturalDatePattern.matcher(paddedInput);
        if (naturalMatcher.matches()) {
            return parseNaturalDate(naturalMatcher);
        }
        return null;
    }

    LocalDateTime parseDateTime(String input) {
        Matcher standardMatcher = standardDateTimePattern.matcher(input);
        if (standardMatcher.matches()) {
            return parseDateTime(standardMatcher.group(0), FORMAT_DEFAULT_DATETIME);
        }

        // The regex pattern uses spaces to match groups.
        // Pad the start and end with spaces to match regex.
        String paddedInput = " " + input + " ";
        Matcher naturalMatcher = naturalDateTimePattern.matcher(paddedInput);
        if (naturalMatcher.matches()) {
            return parseNaturalDateTime(naturalMatcher);
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
    private LocalDateTime parseDateTime(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    private LocalDateTime parseNaturalDate(Matcher match) {
        try {
            int year = Integer.parseInt(match.group(2));
            int day = Integer.parseInt(match.group(1));
            String monthText = match.group(3).toLowerCase().trim();
            int month = (Arrays.asList(MONTH_STRINGS).indexOf(monthText) % 12) + 1;

            return parseDate(String.format("%02d/%02d/%04d", day, month, year));
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            return null;
        }
    }

    private LocalDateTime parseNaturalDateTime(Matcher match) {
        try {
            int year = Integer.parseInt(match.group(2));
            int day = Integer.parseInt(match.group(1));
            String monthText = match.group(3).toLowerCase().trim();
            int month = (Arrays.asList(MONTH_STRINGS).indexOf(monthText) % 12) + 1;

            int hour = Integer.parseInt(match.group(4));
            int minute = Integer.parseInt(match.group(5));

            return parseDateTime(String.format("%02d/%02d/%04d %02d:%02d", day, month, year, hour, minute));
        } catch (NumberFormatException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
