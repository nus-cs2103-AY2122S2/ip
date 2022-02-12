package tsohg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the deadline task.
 */
public class DeadlineTask extends Task {

    private final LocalDate date;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor of the class.
     * @param name Name of the task.
     * @param date Deadline of the task.
     * @throws TsohgException If something wrong happens.
     */
    public DeadlineTask(String name, String date, boolean priority) throws TsohgException {
        super(name, priority);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new TsohgException("Invalid date!");
        }
    }

    /**
     * Returns the string representation of the object.
     * @return The string representation.
     */
    @Override
    public String toString() {
        String dateOutput = String.format("(by: %s)", date.format(pattern));
        return String.format("[D][%s][%s] %s %s", getPriority(), getStatusIcon(),
                name, dateOutput);
    }

    /**
     * Returns the stored data representation of the object.
     * @return The stored data representation.
     */
    @Override
    public String toStore() {
        return String.format("D | %d | %d | %s | %s", isHighPriority ? 1 : 0, isDone ? 1 : 0,
                name, date);
    }
}
