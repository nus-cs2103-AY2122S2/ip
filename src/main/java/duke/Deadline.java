package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * A type of tasks, have a specific date(deadline)
 */
public class Deadline extends Task {

    protected String by;
    private LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.date = LocalDate.parse(by);
    }
    /**
     * Transform the task into the format of data, to be stored into file data
     * @return A formatted string
     */
    @Override
    public String dataFormatOfTask() {
        String bool;
        if(this.isDone) {
            bool = "1";
        } else {
            bool = "0";
        }
        return "D | " + bool + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}