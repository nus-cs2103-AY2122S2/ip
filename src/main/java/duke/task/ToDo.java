package duke.task;

import java.util.StringJoiner;

/**
 * Represents a task without an associated time.
 */
public class ToDo extends Task {
    /**
     * Constructs an instance of a ToDo.
     *
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs an instance of a ToDo.
     *
     * @param description The description of the ToDo.
     * @param isDone      Whether the ToDo is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("T").add(String.valueOf(isDone ? 1 : 0)).add(description);
        return joiner.toString();
    }
}
