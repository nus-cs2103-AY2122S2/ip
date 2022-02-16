package duke.modules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Represents a Task of type Event which includes the event date.
 */
class Event extends Task {

    private final LocalDate time;

    /**
     * Constructor for an Event.
     *
     * @param name The name of the task.
     * @param time The date the task has to be completed by in the format "yyyy-mm-dd".
     */
    public Event(String name, String time) throws DateTimeParseException {
        super(name);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[E][X] " + super.getName() + " (at: "
                    + time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        } else {
            return "[E][ ] " + super.getName() + " (at: "
                    + time.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + ")";
        }
    }
}
