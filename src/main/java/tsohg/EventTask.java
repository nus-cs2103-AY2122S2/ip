package tsohg;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the event task.
 */
public class EventTask extends Task {

    private final LocalDate date;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor of the class.
     * @param name Name of the task.
     * @param date Event date of the task.
     * @throws TsohgException If something wrong happens.
     */
    public EventTask(String name, String date) throws TsohgException {
        super(name);
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
        String dateOutput = String.format("(at: %s)", date.format(pattern));
        return String.format("[E][%s] %s %s", getStatusIcon(), name, dateOutput);
    }

    /**
     * Returns the stored data representation of the object.
     * @return The stored data representation.
     */
    @Override
    public String toStore() {
        return String.format("E | %d | %s | %s", this.isDone ? 1 : 0, this.name, this.date);
    }

}
