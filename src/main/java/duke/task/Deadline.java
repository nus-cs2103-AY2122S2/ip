package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.deadline = time;
    }

    public String getDeadline() {
        return this.deadline.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]")
                .append(super.toString())
                .append(" (by: ")
                .append(this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
