package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of Task class.
 * Denotes a task that is meant to be done during a time duration at a specific day
 */
public class Event extends Task {
    private final DateTimeFormatter inputFormatterStart = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter inputFormatterEnd = DateTimeFormatter.ofPattern("HHmm");
    private final DateTimeFormatter outputFormatterStart = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm -");
    private final DateTimeFormatter outputFormatterEnd = DateTimeFormatter.ofPattern(" HH:mm");
    protected LocalDateTime dateAndStartTime;
    protected LocalTime endTime;
    private String date;
    private String startTime;

    public Event(String description, String dateAndStartTime, String endTime) {
        super(description);
        this.date = dateAndStartTime.split(" ")[0];
        this.dateAndStartTime = LocalDateTime.parse(dateAndStartTime, inputFormatterStart);
        this.endTime = LocalTime.parse(endTime, inputFormatterEnd);
    }

    /**
     * Outputs the formatted time related to the task
     * @return a String in format: MMM dd yyyy, HH:mm - HH:mm
     */
    public String outputTime() {
        return dateAndStartTime.format(outputFormatterStart)
                + endTime.format(outputFormatterEnd);
    }

    /**
     * Outputs the time related to the task in the format that it was initially entered by the user.
     * @return a String in format: dd/MM/yyyy HHmm - HHmm
     */
    public String displayTimeInOriginalFormat() {
        return dateAndStartTime.format(inputFormatterStart) + " - "
                + endTime.format(inputFormatterEnd);
    }

    public void setTime(String newTime) {
        String currDate = this.date;
        String[] details = newTime.split("-");
        String newStart = details[0].trim();
        String newEnd = details[1].trim();
        this.startTime = newStart;
        String updatedDateAndStartTime = currDate + " " + newStart;
        this.dateAndStartTime = LocalDateTime.parse(updatedDateAndStartTime, inputFormatterStart);
        this.endTime = LocalTime.parse(newEnd, inputFormatterEnd);
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + outputTime() + ")";
    }
}
