package kenobi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class encapsulates a Task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a Deadline with the specified name and date.
     *
     * @param name The name of the Deadline.
     * @param by   The deadline of the Deadline.
     * @throws TaskException if the Deadline name is empty.
     */
    public Deadline(String name, LocalDate by) throws TaskException {
        super(name);
        this.by = by;
    }

    /**
     * Returns the type enum corresponding to the Task.
     *
     * @return the type enum corresponding to the Task.
     */
    @Override
    public Type type() {
        return Type.DEADLINE;
    }

    /**
     * Returns a save-friendly string representing the Task.
     *
     * @return a ",.," delimited string representing the Task.
     */
    @Override
    public String toSave() {
        int doneBit = isDone ? 1 : 0;

        return String.format("D,.,%d,.,%s,.,%s\n", doneBit, name, by);
    }

    /**
     * Returns a ui-friendly string representing the Task.
     *
     * @return a ui-friendly string representing the Task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy");

        return String.format("[D]%s (by: %s)", super.toString(), by.format(formatter));
    }
}
