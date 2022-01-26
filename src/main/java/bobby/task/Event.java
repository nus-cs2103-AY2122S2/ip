package bobby.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String at;
    private LocalDate date;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at.substring(at.indexOf(" ") + 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = LocalDate.parse(LocalDate.parse(at, formatter).format(formatter2));
        super.setDate(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
