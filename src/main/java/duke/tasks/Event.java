package duke.tasks;

import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Inherits from Task and is the implementation of a Event task which contains a description and a timing for the event.
 */
public class Event extends Task {
    LocalDateTime timing;

    /**
     * Constructor for Event task.
     *
     * @param task Description of the event
     * @param timing time of the event
     * @throws DukeException if description is empty or timing is not in the correct format
     */
    public Event(String task, String timing) throws DukeException {
        super(task);
        if (timing == null) {
            throw new DukeException("Try Again with correct format!\n");
        }
        this.timing = formatDateTime(timing);
        if (this.timing == null) {
            throw new DukeException("Incorrect Format/Date or Time is out of range\n");
        }
        this.initials = "E";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }
    }

    /**
     * Converts the task into a readable form.
     *
     * @return String containing information on this deadline task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.timing.getDayOfMonth()
                                        + " " + this.timing.getMonth()
                                        + " " + this.timing.getYear()
                                        + ", " + this.timing.getDayOfWeek()
                                        + " " + this.timing.getHour()
                                        + this.timing.getMinute() + ")";
    }

    /**
     * Converts the task into a compact version for storage.
     *
     * @return String containing compact version of the task
     */
    @Override
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = super.makeCompact();
        out.add(timing.toString().replaceFirst("T", " ").replaceAll("-", "/"));
        return out;
    }
}
