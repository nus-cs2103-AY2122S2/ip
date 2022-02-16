package baron.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import baron.exceptions.BaronException;
import baron.message.Message;

/**
 * A utility class used to process DateTime with all static members, mainly used to convert
 * string to LocalDateTime and back.
 *
 * @see LocalDateTime
 */
public class DateTimeUtil {
    /** The format to convert from String to LocalDateTime */
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    /** The format to convert from LocalDateTime to String */
    private static final DateTimeFormatter FORMATTER_DISPLAY = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Returns date/time of {@code LocalDateTime} type given a string.
     *
     * @param dateTimeString the string to be parsed.
     * @return date/time of {@code LocalDateTime} type from {@code dateTimeString}.
     * @throws BaronException If {@code DateTimeParseException} is thrown by {@code LocalDateTime.parse}
     * @see LocalDateTime#parse(CharSequence, DateTimeFormatter)
     */
    public static LocalDateTime getDateTime(String dateTimeString) throws BaronException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeUtil.FORMATTER_INPUT);
        } catch (DateTimeParseException e) {
            throw new BaronException(Message.MESSAGE_DATE_TIME_FORMAT_INVALID);
        }
    }

    /**
     * Returns the formatted date/time string for display from the given {@code LocalDateTime}.
     *
     * @param localDateTime the date/time to be formatted to a string.
     * @return the formatted date/time string for display from the given {@code LocalDateTime}.
     */
    public static String getDisplayString(LocalDateTime localDateTime) {
        assert localDateTime != null : "localDateTime cannot be null";
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeUtil.FORMATTER_DISPLAY);
    }

    /**
     * Returns the formatted date/time string for storage from the given {@code LocalDateTime}.
     *
     * @param localDateTime the date/time to be formatted to a string.
     * @return the formatted date/time string for storage from the given {@code LocalDateTime}.
     */
    public static String getSaveString(LocalDateTime localDateTime) {
        assert localDateTime != null : "localDateTime cannot be null";
        if (localDateTime == null) {
            return "";
        }
        return localDateTime.format(DateTimeUtil.FORMATTER_INPUT);
    }
}
