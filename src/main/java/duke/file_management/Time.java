package duke.file_management;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private LocalDateTime parsedTime;

    public Time(String dateTime) {
        String[] dateTimeArr = dateTime.split(" ");
        String date = dateTimeArr[0];
        String time = dateTimeArr[1];

        this.parsedTime = LocalDateTime.parse(date + "T" + time);
    }

    @Override
    public String toString() {

        return parsedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}
