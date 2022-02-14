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
     * Date and time.
     */
    private LocalDateTime dateTime;

    /**
     * List of acceptable date time patterns.
     */
    private final List<String> dateTimePatterns = List.of(
            "dd[[-][/][:][ ]]MM[[-][/][:][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][/][:][ ]]MM[[-][/][:][ ]]uu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]mmmm[[-][ ]]uu[ hh[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMM[[-][ ]]uu[ HH[[-][:][ ]]MM]",
            "dd[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "dd[[-][ ]]MMMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "d[[-][ ]]MMMM[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]dd[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]d[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]dd[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "MMM[[-][ ]]d[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]dd[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]d[[-][ ]]uuuu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]dd[[-][ ]]uu[ HH[[-][:][ ]]mm]",
            "MMMM[[-][ ]]d[[-][ ]]uu[ HH[[-][:][ ]]mm]");

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
     * Initializes the date and time formatters.
     */
    private void initializeFormatters() {
        inputFormatter = dateTimePatterns.stream()
                .reduce(new DateTimeFormatterBuilder().parseCaseInsensitive(),
                        // Lambdas seem to create checkstyle errors.
                        (builder, str) -> builder.appendOptional(DateTimeFormatter.ofPattern(str)),
                        (builder, other) -> builder.appendOptional(other.toFormatter()))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 8)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_DAY, 0)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
        outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    }

    /**
     * Tries to parse a string into date and time format.
     *
     * @param string String to parse.
     * @throws JukeParseException Throws if string is in wrong format.
     */
    private void parse(String string) throws JukeParseException {
        assert inputFormatter != null;
        if (string == null || string.isEmpty()) {
            throw new JukeParseException("date and time");
        }
        try {
            this.dateTime = LocalDateTime.parse(string, inputFormatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new JukeParseException("date and time");
        }
    }

    /**
     * Returns date and time in the right format.
     *
     * @return Formatted date and time string.
     */
    public String getDateTime() {
        String dt = "";
        if (this.dateTime != null) {
            dt += String.format("%td %tb %tY %tR", this.dateTime, this.dateTime, this.dateTime, this.dateTime);
        }
        return dt;
    }
}
