package chatcat.util;

import chatcat.chatcatexception.InvalidDateException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public class DateTimeUtil {

    LocalDateTime time;

    DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(""
                    + "[yyyy-MM-dd HH:mm]"
                    + "[yyyy/MM/dd HH:mm]"
            ));
    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

    public DateTimeUtil(String time) throws InvalidDateException {
        try {
            this.time = LocalDateTime.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println(OutputMessage.invalidDateMessage());
            throw new InvalidDateException(OutputMessage.invalidDateMessage());
        }
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
