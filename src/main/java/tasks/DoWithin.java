package tasks;
import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoWithin extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public DoWithin(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "W |" + super.toString() + " | " + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
            + " | " + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}