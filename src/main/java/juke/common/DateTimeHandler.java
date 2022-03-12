package juke.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.List;

import juke.exception.JukeParseException;

/**
 * Abstraction for date and time with additional methods.
 */
public class DateTimeHandler {
    /**
     * For initializing a default time value.
     */
    private static final String DEFAULT_TIME = "010102000 0000";

    /**
     * List of acceptable date time patterns.
     */
    private static final List<String> DATE_TIME_PATTERNS = List.of(
            "dd[[-][/][ ]]MM[[-][/][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]dd[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]d[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]dd[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]d[[-][ ]]uuuu[ HH[[-][:][ ]]mm]");

    /**
     * Message for date and time parse error.
     */
    private static final String DATE_TIME_PARSE_ERROR_MESSAGE = "date and time";

    /**
     * Date and time.
     */
    private LocalDateTime dateTime;

    /**
     * Formatter for date time inputs.
     */
    private DateTimeFormatter inputFormatter;

    /**
     * Formatter for date time outputs.
     */
    private DateTimeFormatter outputFormatter;

    /**
     * Constructor that initializes a date and time from a string.
     *
     * @param string String to parse date and time.
     * @throws JukeParseException Throws if string is in wrong format.
     */
    public DateTimeHandler(String string) throws JukeParseException {
        initializeFormatters();
        parse(string);
    }

    /**
     * Constructor that initializes to a default value (1 January 2000, 00:00).
     */
    public DateTimeHandler() {
        initializeFormatters();
        try {
            dateTime = LocalDateTime.parse(DEFAULT_TIME, inputFormatter);
        } catch (DateTimeParseException e) {
            // Should not reach here
            assert false;
            e.printStackTrace();
        }
        assert dateTime != null;
    }

    /**
     * Initializes the date and time formatters.
     */
    private void initializeFormatters() {
        inputFormatter = DATE_TIME_PATTERNS.stream()
                // Lambdas seem to create checkstyle errors
                .reduce(new DateTimeFormatterBuilder().parseCaseInsensitive(),
                        (builder, str) -> builder.appendOptional(DateTimeFormatter.ofPattern(str)),
                        (builder, other) -> builder.appendOptional(other.toFormatter()))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 8)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
        outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
    }

    /**
     * Tries to parse a string into date and time format.
     *
     * @param string String to parse.
     * @throws JukeParseException Throws if string is in wrong format.
     */
    private void parse(String string) throws JukeParseException {
        assert inputFormatter != null;
        boolean isInputEmpty = string == null || string.isEmpty();
        if (isInputEmpty) {
            throw new JukeParseException(DATE_TIME_PARSE_ERROR_MESSAGE);
        }
        try {
            dateTime = LocalDateTime.parse(string, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new JukeParseException(DATE_TIME_PARSE_ERROR_MESSAGE);
        }
    }

    /**
     * Returns date and time in the right format.
     *
     * @return Formatted date and time string.
     */
    public String getDateTime() {
        return dateTime.format(outputFormatter);
    }
}
