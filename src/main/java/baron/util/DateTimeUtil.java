package baron.util;

import baron.exceptions.BaronException;
import baron.messages.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtil {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    private static final DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    public static LocalDateTime getDateTime(String dateTimeString) throws BaronException {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeUtil.inputFormatter);
        } catch (DateTimeParseException e) {
            throw new BaronException(Messages.MESSAGE_DATE_TIME_FORMAT_INVALID);
        }
    }

    public static String getDisplayString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeUtil.displayFormatter);
    }

    public static String getSaveString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeUtil.inputFormatter);
    }
}
