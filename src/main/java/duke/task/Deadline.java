package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * A class that represents a task with deadline.
 */
public class Deadline extends Task {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy HHmm";
    private static final String SAVE_FILE_DATE_FORMAT = "d/MM/yyyy HHmm";
    private final LocalDateTime dueBy;

    /**
     * Creates a Deadline instance with a title and due date.
     *
     * @param title The title of the deadline.
     * @param dueBy The due date of the deadline.
     */
    public Deadline(String title, LocalDateTime dueBy) {
        super(title);
        this.dueBy = dueBy;
    }

    /**
     * Retrieves and parses the save format for deadline.
     *
     * @return The save format for deadline.
     * @throws DukeException If the class return an invalid class type,
     * it will throw a DukeException.
     */
    @Override
    public String getSaveFormat() throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(SAVE_FILE_DATE_FORMAT);
        return String.format("%s | %s", super.getSaveFormat(), this.dueBy.format(format));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return String.format("[D]%s (by: %s)", super.toString(), this.dueBy.format(format));
    }
}
