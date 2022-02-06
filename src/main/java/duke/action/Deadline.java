package duke.action;

import duke.ui.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An extension from Action, Deadline has a date variable of
 * type LocalDateTime that is specific to itself.
 */
public class Deadline extends Action {

    private LocalDateTime date;

    /**
     * Constructs a new Deadline object by taking in
     * String variables, task and by.
     * Etc; task = "some task" and by = "2010-10-10 10:10"
     * @param task task with deadline
     * @param by date and time values of the task with deadline
     */
    public Deadline(String task, String by)  {
        super(task);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        this.date = LocalDateTime.parse(by, format);
    }

    /**
     * Constructs a new Deadline object by taking in
     * String variable, task,LocalDateTime variable
     * by and boolean variable, bool.
     * @param task task with deadline
     * @param by date and time values
     * @param bool done status of task with deadline
     */
    public Deadline(String task, LocalDateTime by, boolean bool)  {
        super(task, bool);
        this.date = by;
    }

    /**
     * Returns a new Deadline object with same variable values except
     * isDone which is now true
     * @return marked Deadline object
     */
    @Override
    public Action setDone()  {
        return new Deadline(act, date, true);
    }

    /**
     * Returns a new Deadline object with same variable values except
     * isDone which is now false
     * @return unmarked Deadline object
     */
    @Override
    public Action setUnDone()  {
        return new Deadline(act, date, false);
    }

    /**
     * Returns a string representation the Deadline object.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy, H:m")) + ")";
    }
}