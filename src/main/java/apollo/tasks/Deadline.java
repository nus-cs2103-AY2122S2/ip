package apollo.tasks;

import apollo.parser.Parser;

import java.time.LocalDateTime;

/**
 * Class for {@code Deadline} tasks.
 * Extends {@code Task} superclass.
 */
public class Deadline extends Task {

    private final LocalDateTime time;

    /**
     * Constructor for {@code Deadline}.
     *
     * @param description Of task.
     * @param time Date and time when task is due.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, Type.DEADLINE);
        this.time = time;
    }

    /**
     * Adds String representation of date and time of deadline to task.
     *
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(),
                this.time.format(Parser.formatter));
    }
}
