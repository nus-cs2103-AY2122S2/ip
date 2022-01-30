package duke.file_management;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a Time object for the inputted date and time when Event or Deadline task is used
 *
 * @author Justin Ng Jie Ern
 */
public class Time {

    /**
     * parsedTime for the particular Task
     */
    private LocalDateTime parsedTime;

    /**
     * Constructor for Time Object
     *
     * @param dateTime String of date and time that is inputted by user
     */
    public Time(String dateTime) {
        String[] dateTimeArr = dateTime.split(" ");
        String date = dateTimeArr[0];
        String time = dateTimeArr[1];

        this.parsedTime = LocalDateTime.parse(date + "T" + time);
    }

    /**
     * Prints out Time Object in a "MMM d yyyy, HH:mm" format
     *
     * @return String of Time Object.
     */
    @Override
    public String toString() {

        return parsedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }
}
