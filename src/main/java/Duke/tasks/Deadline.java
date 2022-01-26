package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.ui.DukeException;
/**
 * Represent the deadline of task.
 */

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Deadline.
     *
     * @param description description of the task.
     * @param by by when the deadline end.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.date = super.getTaskDate(by);
        this.time = super.getTaskTime(by);
    }

    /**
     * The String representation of Deadline in the save file.
     *
     * @return the formats of the String to be save in the file
     */
    @Override
    public String saveToFileString() {
        return "D|" + (isDone ? "1|" : "0|") + super.getDescription() + "|" + date + " "
                + time + "\n";
    }

    /**
     * The String representation of Deadline.
     *
     * @return [D] with the status and description of the task,
     *         and by when.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                +  " " + time + ")";
    }
}

