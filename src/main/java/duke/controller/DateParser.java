package duke.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import duke.utility.datetime.TimeStandard;
import duke.utility.datetime.DayFormat;
import duke.utility.datetime.StandardTimeStandard;
import duke.utility.datetime.StandardTimeFormat;
import duke.exception.DateFormatException;
import duke.exception.DukeException;


public class DateParser implements Parser<LocalDateTime> {
    private static final List<TimeStandard> DATE_TIME_FORMAT_LIST =
            Arrays.asList(new DayFormat(), new StandardTimeStandard(), new StandardTimeFormat());
    private String date;

    public DateParser(String date) {
        this.date = date;
    }

    @Override
    public LocalDateTime parse() throws DukeException {
        LocalDateTime ldt;
        for (TimeStandard format: DATE_TIME_FORMAT_LIST) {
            if (format.isTime(date)) {
                ldt = format.mapToLocalDateTime(date);
                return ldt;
            }
        }
        throw new DateFormatException("The date format is not valid.");
    }
}
