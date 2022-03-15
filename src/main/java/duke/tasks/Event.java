package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for event type task
 * stores the due date of the task
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Constructor for Event task
     * @param detail details of the task
     * @param date date of which the task is due
     */
    public Event(String detail, String date) {
        super(detail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.date = LocalDateTime.parse(date, formatter);
    }

    /**
     * Returns the due date of the task
     * @return Date if applicable
     */
    @Override
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return date.format(formatter);
    }

    /**
     * returns a string representation for the type of task
     * @return String representing the type of task
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Updates the details of the task
     */
    @Override
    public void updateDetail(String update) {
        this.detail = update;
    }

    /**
     * Updates the date of the task
     */
    @Override
    public void updateDate(String update) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime updateddate = LocalDateTime.parse(update, formatter);
        this.date = updateddate;
    }

    /**
     * returns string representation of Task with mark status date and detail
     * @return String representation of Task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String markedPrint;
        if (isMarked) {
            markedPrint = "[E][X] ";
        } else {
            markedPrint = "[E][ ] ";
        }
        return markedPrint + detail + "(at:" + date.format(formatter) + ")";
    }
}
