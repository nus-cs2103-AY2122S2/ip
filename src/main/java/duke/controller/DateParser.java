package duke.controller;

import duke.exception.DateFormatException;
import duke.exception.DukeException;
import duke.utility.datetime.DayFormat;
import duke.utility.datetime.StandardTimeFormat;
import duke.utility.datetime.StandardTimeStandard;
import duke.utility.datetime.TimeStandard;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * class for parsing date format
 */
public class DateParser implements Parser<LocalDateTime> {

    private static final List<TimeStandard> DATE_TIME_FORMAT_LIST =
            Arrays.asList(new DayFormat(), new StandardTimeStandard(), new StandardTimeFormat());
    private String date;
    public DateParser(String date) {
        this.date = date;
    }

    @Override
    public LocalDateTime parse() throws DukeException {
        LocalDateTime localDateTime;
        for (TimeStandard format: DATE_TIME_FORMAT_LIST) {
            if (format.isTime(date)) {
                localDateTime = format.mapToLocalDateTime(date);
                return localDateTime;
            }
        }
        throw new DateFormatException("The date format is not valid.");
    }
}
