package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task made by the user. A <code>Deadline</code> object stores
 * the description,date and time of the task that need to be done before a certain date.
 * The task can be mark as done after the user complete the specific task.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    /**
     * Creates an instance of a Deadline object.
     *
     * @param description the description of the deadline task.
     * @param dueDate due date of deadline task.
     * @param dueTime due time of deadline task.
     */
    public Deadline(String description, LocalDate dueDate, LocalTime dueTime) {
        super(description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Creates an instance of a Deadline object.
     *
     * @param description the description of the deadline task.
     * @param isMark whether the deadline task is marked.
     * @param dueDate due date of deadline task.
     * @param dueTime due time of deadline task.
     */
    public Deadline(String description, boolean isMark, LocalDate dueDate, LocalTime dueTime) {
        super(description, isMark);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

    /**
     * Returns the visual description of the deadline task.
     *
     * @return description of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d MMM yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mma");

        return String.format("[D]%s (by: %s %s)", super.toString(), dueDate.format(dateFormat),
                dueTime.format(timeFormat));
    }

    /**
     * Returns the data of the deadline task.
     *
     * @return data of the deadline task.
     */
    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + dueDate + "|" + dueTime;
    }
}
