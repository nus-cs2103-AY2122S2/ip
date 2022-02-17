package duke.task;

import java.time.LocalDate;
import  java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor to create an duke.task.Event duke.task.Task.
     */
    public Event(String name, String at) {
        super(name);
        at = at.trim();
        this.at = LocalDate.parse(at);
    }

    /**
     *
     * @param name
     * @param at
     * @param done
     */
    public Event(String name, LocalDate at, boolean done) {
        super(name, done);
        this.at = at;
    }

    /**
     * Getter for the date of the duke.task.Event.
     * @return The date of the duke.task.Event.
     */
    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
         return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}