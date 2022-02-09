package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public interface Timeable {
    LocalDate getDate();

    String getDateString(DateTimeFormatter dateTimeFormat);

    boolean isSameDate(LocalDate date);

    static LocalDate of(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
    }

    static DateTimeFormatter getPrintableFormat() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy");
    }

    static DateTimeFormatter getWritableFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

}
