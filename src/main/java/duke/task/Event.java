package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a start date, as well as an optional start time and end time.
 *
 * @author Terng Yan Long
 */

public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    /**
     * Instantiates a new event task.
     * By default, the new task is set to "not done".
     * This task has a specific date as well as a start and end time.
     *
     * @param description Describes what needs to be done in this task.
     * @param eventDate Date of event.
     * @param eventStartTime Start time of event.
     * @param eventEndTime End time of event.
     */
    public Event(String description, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime) {
        super(description);
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    /**
     * Prepends a checkbox "[E]" to the front of the task, which indicates the type of task.
     *
     * @return String containing a type icon that is prepended in front of the task description.
     */
    @Override
    public String toString() {
        String formattedDate = eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedStartTime = "";
        String formattedEndTime = "";
        if (eventStartTime != null) {
            formattedStartTime = " " + eventStartTime.toString();
        }
        if (eventEndTime != null) {
            formattedEndTime = "-" + eventEndTime.toString();
        }
        return "[E]" + super.toString() + " (at: "
                + formattedDate + formattedStartTime + formattedEndTime + ")";
    }

}
