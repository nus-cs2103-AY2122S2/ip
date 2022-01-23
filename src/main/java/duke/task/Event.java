package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeWrongInputFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final String SCHEDULED_TIME_STRING;
    private final LocalDateTime SCHEDULED_TIME;

    public Event(String description, String scheduledTime) throws DukeException {
        super(description);
        this.SCHEDULED_TIME = parseScheduledTime(scheduledTime);
        this.SCHEDULED_TIME_STRING = formatScheduledTime();
    }


    private LocalDateTime parseScheduledTime(String scheduledTime) throws DukeException {
        String[] temp = scheduledTime.split(" ", 2);
        if (temp.length <= 1 || temp[1].length() < 4) {
            throw new DukeWrongInputFormatException("Format for deadline is wrong. Please refer to list of commands.");
        }
        try {
            return LocalDateTime.parse(temp[0] + "T" + temp[1].charAt(0) + temp[1].charAt(1)
                    + ":" + temp[1].charAt(2) + temp[1].charAt(3) + ":00");
        } catch (DateTimeParseException e) {
            throw new DukeWrongInputFormatException("Format for event is wrong. Please refer to list of commands.");
        }
    }

    private String formatScheduledTime() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return SCHEDULED_TIME.format(form);
    }

    @Override
    public String formatSave() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String date = this.SCHEDULED_TIME.format(form);
        return "E |" + (super.isDone ? "1| " : "0| ") + super.description + " /at " + date;
    }


    /*
     * Customized toString method for duke.task.Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.SCHEDULED_TIME_STRING + ")";
    }
}
