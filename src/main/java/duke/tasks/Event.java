package duke.tasks;

import duke.exceptions.InvalidOperationException;

import java.time.LocalDate;

/**
 * Task Object of type Event.
 * An Event Object can hold a String description, a LocalDate date,
 * and a boolean isDone.
 */
public class Event extends Task {
    private String item;
    private LocalDate date;
    private boolean isDone;

    /**
     * Constructs the Event Object.
     *
     * @param details Description of the task
     * @param date String to be converted to a LocalDate object
     */
    public Event(String details, String date) {
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
        if (this.isDone) {
            throw new InvalidOperationException("marked");
        }
        this.isDone = true;
    }

    /**
     * Marks the boolean isDone as false.
     *
     * @throws InvalidOperationException if isDone is already true
     */
    @Override
    public void unmark() throws InvalidOperationException {
        if (!this.isDone) {
            throw new InvalidOperationException("unmarked");
        }
        this.isDone = false;

    }

    /**
     * @return a String representation of the Event Object
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[E]"+"[X] " + item + "(at: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
        else {
            return "[E]"+"[ ] " + item + "(at: " +
                    date.getDayOfMonth() +
                    " " +
                    date.getMonth() +
                    " " +
                    date.getYear() +
                    ")";
        }
    }
}
