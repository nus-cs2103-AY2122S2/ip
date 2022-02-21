package athena.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single deadline in a task list.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    private final LocalDateTime dueDateTime;

    /**
     * Constructs a new deadline with the given name and dueDateTime.
     *
     * @param name The name of the deadline.
     * @param dueDateTime The due date and time of the deadline.
     */
    public Deadline(String name, LocalDateTime dueDateTime) {
        super(name);
        this.dueDateTime = dueDateTime;
    }

    /**
     * Constructs a new deadline with the given name and dueDateTime String.
     *
     * @param name The name of the deadline.
     * @param dueDateTime The dueDateTime String of the deadline in MMM d yyyy hh:mma format.
     *                    (see LocalDateTime docs for notation)
     */
    public Deadline(String name, String dueDateTime) {
        super(name);
        this.dueDateTime = LocalDateTime.parse(dueDateTime, OUTPUT_FORMATTER);
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getSaveFormat() {
        String dueDateTimeString = dueDateTime.format(OUTPUT_FORMATTER);
        return String.join(SAVE_SEPARATOR, super.getSaveFormat(), dueDateTimeString);
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getIcon() {
        return "D";
    }

    /**
     * Returns true if the deadline falls within the given time period.
     *
     * @param startDate The startDate of the time period.
     * @param endDate The endDate of the time period.
     * @return True if deadline falls within given time period, inclusive of endDAte
     */
    @Override
    public boolean isFallingBetweenInclusive(LocalDate startDate, LocalDate endDate) {
        LocalDate dueDate = dueDateTime.toLocalDate();
        if (dueDate.isBefore(startDate) || dueDate.isAfter(endDate)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @inheritDocs
     */
    @Override
    public String toString() {
        String dueDateTimeString = dueDateTime.format(OUTPUT_FORMATTER);
        return super.toString() + String.format(" (by: %s)", dueDateTimeString);
    }
}
