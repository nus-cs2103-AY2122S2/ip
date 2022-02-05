package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.dukeexceptions.DeadlineException;

/**
 * A type of tasks, have a specific ddate
 */
public class Event extends Task {

    protected String at;
    private LocalDate date;

    /**
     * Constructor.
     *
     * @param description description
     * @param at date
     * @throws DeadlineException deadline exception
     */
    public Event(String description, String at) throws DeadlineException {
        super(description);
        try {
            this.at = at;
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("Sorry I am stupid, you need to tell me the date as this format:Year-Month-Day "
                                                + "e.g: 2022-02-15");
        }
    }

    /**
     * Constructor when loading data.
     *
     * @param description description
     * @param at date
     * @throws DeadlineException deadline exception
     */
    public Event(String description, String at, boolean isDone) throws DeadlineException {
        super(description, isDone);
        try {
            this.at = at;
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DeadlineException("Sorry I am stupid, you need to tell me the date as this format:Year-Month-Day "
                                                + "e.g: 2022-02-15");
        }
    }

    /**
     * Transform the task into the format of data, to be stored into file data
     * @return A formatted string
     */
    @Override
    public String dataFormatOfTask() {
        String bool;
        if (this.isDone) {
            bool = "1";
        } else {
            bool = "0";
        }
        return "E | " + bool + " | " + this.description + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
