package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    private String dateToString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    String getTaskType() {
        return "D";
    }

    String getDate() {
        return deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String toString() {
        return String.format("[%s]", getTaskType())
                + super.toString()
                + String.format(" (by: %s)", dateToString());
    }
}
