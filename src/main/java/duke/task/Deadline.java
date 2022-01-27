package duke.task;

import duke.DukeDateTime;

public class Deadline extends Task {

    protected static Icon ICON = Icon.D;
    protected final DukeDateTime by;

    /**
     * Constructs a {@code Deadline} object with the specified description and deadline
     * @param description the description of the deadline task
     * @param by a {@code DukeDateTime} object specifying the deadline
     */
    public Deadline(String description, DukeDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a {@code Deadline} object with the specified description, status and deadline
     * @param description the description of the deadline task
     * @param isDone a boolean indicating whether the task is done
     * @param by a {@code DukeDateTime} object specifying the deadline
     */
    public Deadline(String description, Boolean isDone, DukeDateTime by) {
        super(description, isDone);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[" + ICON + "]" + super.toString() + " (by: " + by.format("d MMM yyyy") + ")";
    }

    @Override
    public Icon getIcon() {
        return ICON;
    }

    @Override
    public String getDescription() {
        return description + " /by " + by.format("d MMM yyyy");
    }

    @Override
    public Deadline mark() {
        return new Deadline(description, true, by);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(description, false, by);
    }

}
