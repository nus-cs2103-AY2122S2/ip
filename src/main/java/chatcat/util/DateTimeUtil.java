package chatcat.util;

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

    public DateTimeUtil(String time) {
        try {
            this.time = LocalDateTime.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}
