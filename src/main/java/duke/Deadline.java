package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate time;

    /**
     * Class constructor.
     * The task generated is by default not done.
     *
     * @param content description of the task.
     * @param time deadline of the task.
     */
    public Deadline(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    /**
     * Class Constructor.
     *
     * @param content description of the task.
     * @param time deadline of the task.
     * @param isDone whether the task is done.
     */
    public Deadline(String content, LocalDate time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    @Override
    public Deadline mark(boolean isDone) {
        return new Deadline(super.getContent(), time, isDone);
    }

    @Override
    public String fileFormat() {
        return "D" + super.fileFormat() + time + " | ";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + time.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)) + ")";
    }
}
