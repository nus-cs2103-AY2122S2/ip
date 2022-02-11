package duke.task;

import duke.DukeDateTime;

public class Todo extends Task {

    protected static Icon ICON = Icon.T;

    /**
     * Constructs a {@code Todo} object with the specified description
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} object with the specified description and status
     * @param description the description of the todo task
     * @param isDone a boolean indicating whether the task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + ICON + "]" + super.toString();
    }

    @Override
    public String toStringRecord() {
        return ICON + " " + super.toStringRecord();
    }

    @Override
    public Icon getIcon() {
        return ICON;
    }

    @Override
    public Todo mark() {
        return new Todo(description, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(description, false);
    }

}
