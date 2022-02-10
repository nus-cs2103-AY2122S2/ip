package chatcat.util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


public class DateTimeUtil {

    LocalDate time;

    DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(""
                    + "[yyyy-MM-dd HH:mm:ss]"
                    + "[yyyy-MM-dd]"
                    + "[yyyy/MM/dd]"
                    + "[yyyy-MM-dd HH:mm a]"
            ));
    DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

    public DateTimeUtil(String time) {
        try {
            this.time = LocalDate.parse(time, dateTimeFormatter);
        } catch (DateTimeException de) {
            System.out.println("sorry, this is not a valid time");
        }
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
