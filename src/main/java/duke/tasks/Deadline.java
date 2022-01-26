package duke.tasks;
import duke.exceptions.InvalidOperationException;

import java.time.LocalDate;

/**
 * Task Object of type Deadline.
 * A Deadline Object can hold a String description, a LocalDate date,
 * and a boolean isDone.
 */
public class Deadline extends Task {
    private String item;
    private LocalDate date;
    private boolean done;

    /**
     * Constructs the Deadline Object.
     *
     * @param details Description of the task
     * @param date String to be converted to a LocalDate object
     */
    public Deadline(String details, String date) {
        this.item = details;
        date = date.stripLeading();
        this.date = LocalDate.parse(date);
    }

    /**
     * Marks the boolean isDone as true.
     *
     * @throws InvalidOperationException if isDone is already true
     */
    @Override
    public void mark() throws InvalidOperationException {
        if (this.done) {
            throw new InvalidOperationException("marked");
        }
        this.done = true;

    }

    /**
     * Marks the boolean isDone as false.
     *
     * @throws InvalidOperationException if isDone is already true
     */
    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.done) {
            throw new InvalidOperationException("unmarked");
        }
        this.done = false;

    }

    /**
     * @return a String representation of the Deadline Object
     */
    @Override
    public String toString() {
        if (done) {
            return "[D]"+"[X] " + item + "(by: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
        else {
            return "[D]"+"[ ] "+ item + "(by: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
    }
}
