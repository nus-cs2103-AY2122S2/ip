package kenobi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates a Task that happens at a given time.
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Constructs an Event with the specified name and date.
     *
     * @param name The name of the Event.
     * @param at   The time of the Event.
     * @throws TaskException if the Event name is empty.
     */
    public Event(String name, LocalDate at) throws TaskException {
        super(name);
        this.at = at;
    }

    /**
     * Returns the type enum corresponding to the Task.
     *
     * @return the type enum corresponding to the Task.
     */
    @Override
    public Type type() {
        return Type.EVENT;
    }

    /**
     * Returns a save-friendly string representing the Task.
     *
     * @return a ",.," delimited string representing the Task.
     */
    @Override
    public String toSave() {
        int doneBit = isDone ? 1 : 0;

        return String.format("E,.,%d,.,%s,.,%s\n", doneBit, name, at);
    }

    /**
     * Returns a ui-friendly string representing the Task.
     *
     * @return a ui-friendly string representing the Task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy");

        return String.format("[E]%s (at: %s)", super.toString(), at.format(formatter));
    }
}
