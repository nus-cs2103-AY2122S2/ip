package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    String time;
    LocalDate date;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
        try {
            date = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            date = null;
        }
    }

    public void print() {
        System.out.print("[D]");
        System.out.print("[" + (this.completed ? "x" : " ") +  "] " + this.description);
        if (date == null) {
            System.out.println(" (by: " + this.time + ")");
        } else {
            System.out.println(" (by: " + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")");
        }
    }

    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[0] = TaskType.DEADLINE.toString();
        details[3] = time;
        return details;
    }
}
