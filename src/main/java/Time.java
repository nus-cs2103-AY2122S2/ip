import org.w3c.dom.html.HTMLImageElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private String date;
    private String time;
    private LocalDateTime parsedTime;

    public Time(String dateTime) {
//        try {
            String[] dateTimeArr = dateTime.split(" ");
            String date = dateTimeArr[0];
            String time = dateTimeArr[1];
//            this.date = date;
//            this.time = time;

            this.parsedTime = LocalDateTime.parse(date + "T" + time);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("An invalid time has been added. Please use a YYYY-MM-DD HH:MM format.");
//        }
    }

    public LocalDateTime getParsedTime() {
        return parsedTime;
    }

    @Override
    public String toString() {

        return parsedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}
