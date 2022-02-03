package duke.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public Parser() {}

    /**
     * Parses date and time in string format into LocalDateTime instance.
     *
     * @param by Deadline in string format.
     * @return LocalDateTime instance.
     */
    public static LocalDateTime parseDate(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return LocalDateTime.parse(by.trim(), formatter);
    }
}
