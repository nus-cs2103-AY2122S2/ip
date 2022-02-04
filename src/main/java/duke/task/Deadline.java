package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.DukeException;

public class Deadline extends Task {

    /** The `Date` of when this `Deadline` is held. */
    private final Date by;

    /**
     * Instantiates a new Deadline.
     *
     * @param taskName the task name/description
     * @param by       the date of when this `Deadline` is due
     * @throws DukeException if the `String` representation of the date does not follow the format
     */
    public Deadline(String taskName, String by) throws DukeException {
        super(taskName);
        try {
            this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param taskName the task name/description
     * @param by       the date of when this `Deadline` is due
     * @param isMarked the is `Deadline` has been marked/completed
     * @throws DukeException if the `String` representation of the date does not follow the format
     */
    public Deadline(String taskName, String by, String isMarked) throws DukeException {
        super(taskName);
        try {
            this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
            this.isMarked = (isMarked.equals("1"));
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    /**
     * Returns the `String` representation of a `Deadline`.
     *
     * @return the `String` representation of a `Deadline`
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy @ hh:mm a");
        return String.format("[D]%s (by: %s)", super.toString(), sdf.format(this.by));
    }

    /**
     * Returns the `String` representation of a `Deadline` that is written to the local data file.
     *
     * @return the `String` representation of a `Deadline` that is written to the local data file
     */
    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("%s | %s | %s | %s\n", "D", markStatus, super.taskName, sdf.format(this.by));
    }
}
