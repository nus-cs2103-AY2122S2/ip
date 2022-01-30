package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.ui.DukeException;
/**
 * Represents the event of task.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Event.
     *
     * @param description description of the task.
     * @param at by when the event at.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.date = super.getTaskDate(at);
        this.time = super.getTaskTime(at);
    }

    /**
     * Strings representation of Event in the save file.
     *
     * @return the formats of the String to be save in the file.
     */
    @Override
    public String saveToFileString() {
        return "E|" + (isDone ? "1|" : "0|") + super.getDescription() + "|" + date + " "
                + time + "\n";
    }

    /**
     * Strings representation of event.
     *
     * @return [E] with the status and description of the task,
     *         and at when.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }
}

