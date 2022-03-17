package duke.utility.datetime;

import duke.exception.DateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class StandardTimeFormat implements TimeStandard {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                    .optionalStart().appendPattern(" HH:mm")
                    .toFormatter();
    @Override
    public boolean isTime(String date) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public LocalDateTime mapToLocalDateTime(String date) throws DateFormatException {
        try {
            LocalDateTime time = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
            return time;
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Please use correct date format");
        }
    }
}
