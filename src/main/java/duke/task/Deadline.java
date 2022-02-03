package duke.task;

import duke.dukeexceptions.DeadlineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of tasks, have a specific date(deadline)
 */
public class Deadline extends Task {

    protected String by;
    private LocalDate date;

    public Deadline(String description, String by) throws DeadlineException {
        super(description);
        try {
            this.by = by;
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("Sorry I am stupid, you need to tell me the date as this format:Year-Month-Day e.g: 2022-02-15");
        }
    }

    public Deadline(String description, String by, boolean isDone) throws DeadlineException  {
        super(description, isDone);
        try {
            this.by = by;
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("Sorry I am stupid, you need to tell me the date as this format:Year-Month-Day e.g: 2022-02-15");
        }
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