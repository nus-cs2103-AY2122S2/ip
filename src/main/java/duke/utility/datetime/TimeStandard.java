package duke.utility.datetime;

import duke.exception.DateFormatException;

import java.time.LocalDateTime;


public interface TimeStandard {
    boolean isTime(String time);
    LocalDateTime mapToLocalDateTime(String time) throws DateFormatException;
}
