package bobby.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private LocalDate date;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by.substring(by.indexOf(" ") + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(LocalDate.parse(by, formatter).format(formatter2));
        super.setDate(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
