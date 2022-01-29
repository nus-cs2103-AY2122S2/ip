package duke.task;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    /** The `Date` of when this `Event` is held. */
    private final Date at;

    /**
     * Instantiates a new Event.
     *
     * @param taskName the task name/description
     * @param at       the date of when this `Event` is held
     * @throws DukeException if the `String` representation of the date does not follow the format
     */
    public Event(String taskName, String at) throws DukeException {
        super(taskName);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    /**
     * Instantiates a new Event.
     *
     * @param taskName the task name/description
     * @param at       the date of when this `Event` is held
     * @param isMarked the is `Event` has been marked/completed
     * @throws DukeException if the `String` representation of the date does not follow the format
     */
    public Event(String taskName, String at, String isMarked) throws DukeException {
        super(taskName);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
            this.isMarked = (isMarked.equals("1"));
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    /**
     * Returns the `String` representation of an `Event`.
     *
     * @return the `String` representation of an `Event`
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy @ hh:mm a");
        return String.format("[E]%s (at: %s)", super.toString(), sdf.format(this.at));
    }

    /**
     * Returns the `String` representation of an `Event` that is written to the local data file.
     *
     * @return the `String` representation of an `Event` that is written to the local data file
     */
    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("%s | %s | %s | %s\n", "E", markStatus, super.taskName, sdf.format(this.at));
    }
}
