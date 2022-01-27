package johnny;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Overrides parent method to return
     * string that can be written to the text
     * file (formatted for Deadline objects)
     *
     * @return String to write to file.
     */
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
