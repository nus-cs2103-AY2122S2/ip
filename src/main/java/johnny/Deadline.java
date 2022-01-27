package johnny;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskString() {
        return "DEADLINE" + "," + super.isDone + "," + super.description + "," + this.dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "[D] [" + this.getStatusIcon() + "] " +
                super.getDescription() + " (" +
                this.getDueDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
