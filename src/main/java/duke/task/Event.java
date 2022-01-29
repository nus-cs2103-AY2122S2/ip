package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private String scheduledTimeString;
    private LocalDateTime scheduledTime;

    /**
     * Returns an event Task object and accepts a String as description and a String to indicate the scheduledTime.
     *
     * @param description Description of the task.
     * @param scheduledTime Scheduled time of the task.
     */
    public Event(String description, String scheduledTime) throws DukeException {
        super(description);
        this.scheduledTime = parseScheduledTime(scheduledTime);
        this.scheduledTimeString = formatScheduledTime();
    }

    /**
     * Converts the scheduled time from a String to a LocalDateTime object and returns it.
     *
     * @param scheduledTime Deadline of the task.
     * @return scheduled time as a LocalDateTime.
     */
    private LocalDateTime parseScheduledTime(String scheduledTime) throws DukeException {
        String[] temp = scheduledTime.split(" ", 2);
        if (temp.length <= 1 || temp[1].length() < 4) {
            throw new DukeWrongInputFormatException("Format for event is wrong. Please refer to list of commands.");
        }
        try {
            return LocalDateTime.parse(temp[0] + "T" + temp[1].charAt(0) + temp[1].charAt(1)
                    + ":" + temp[1].charAt(2) + temp[1].charAt(3) + ":00");
        } catch (DateTimeParseException e) {
            throw new DukeWrongInputFormatException("Format for event is wrong. Please refer to list of commands.");
        }
    }

    /**
     * Returns a formatted String based on the pattern MMM dd yyyy HH:mm.
     *
     * @return Formatted String.
     */
    private String formatScheduledTime() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return scheduledTime.format(form);
    }

    /**
     * Returns a formatted String to be saved in a file.
     *
     * @return Formatted String for saving.
     */
    @Override
    public String formatSave() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String date = this.scheduledTime.format(form);
        return "E |" + (super.isDone ? "1| " : "0| ") + super.description + " /at " + date;
    }

    /**
     * Returns a String to display the type, the done status of task as well as the task description.
     *
     * @return Formatted String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.scheduledTimeString + ")";
    }
}
