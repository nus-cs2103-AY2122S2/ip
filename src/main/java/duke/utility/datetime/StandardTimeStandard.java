package duke.utility.datetime;


import duke.exception.DateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;



public class StandardTimeStandard implements TimeStandard {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
                    .optionalStart().appendPattern(" HH:mm")
                    .optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_DAY, 0).toFormatter();

    @Override
    public boolean isTime(String date) {
        try {
            LocalDate ldt = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public LocalDateTime mapToLocalDateTime(String date) throws DateFormatException {
        try {
            LocalDateTime ld = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
            return ld;
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Please use valid date format");
        }

    }
}