package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadline(String name, LocalDate dueDate, LocalTime dueTime) {
        super(name);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    public Deadline(String name, boolean isMark, LocalDate dueDate, LocalTime dueTime) {
        super(name, isMark);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");

        return String.format("[D]%s (by: %s %s)", super.toString(), dueDate.format(dateFormat),
                dueTime.format(timeFormat));
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + dueDate + "|" + dueTime;
    }
}
