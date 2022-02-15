package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final String sym = "D";
    private LocalDate day;

    Deadline (String description, String day) {
        super(description);
        this.day = LocalDate.parse(day);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]%s (by:%s)", sym, super.getStatusIcon(), super.getDescription(),
                day.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
    }
}
