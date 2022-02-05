package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline created by the user that are/will be stored in the database.
 * A <code>Deadlines</code> inherits from <code>Tasks</code> and is represented by its
 * name, completion status, and the deadline of the event e.g.,
 * <code>"Eat lunch", true, "2012-06-03"</code>
 */
public class Deadlines extends Tasks {
    LocalDate deadline; // Deadline to complete deadline duke.task

    // Constructor
    public Deadlines(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    public Deadlines(String taskName, boolean completion, String deadline) throws DateTimeParseException {
        super(taskName, completion);
        this.deadline = LocalDate.parse(deadline);
    }

    // Get deadline of duke.task
    String getTiming() {
        return "(by: " + deadline + ")";
    }

    /**
     * Returns a new completed instance of the task.
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Deadlines completeTask() {
        return new Deadlines(super.getName(), true, deadline.toString());
    }

    /**
     * Returns a new uncompleted instance of the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Deadlines uncompleteTask() {
        return new Deadlines(super.getName(), false, deadline.toString());
    }

    // Save to database format
    public String toDatabaseString() {
        return "D | " + (this.getCompletion() == true ? "X" : " ") + " | " + super.getName()
                + " | " + deadline + "\n";
    }

    // toString returning duke.task
    public String toString() {
        return "[D][" + (this.getCompletion() == true ? "X" : " ") + "] " + super.getName()
                + " (by: " + deadline + ")";
    }

}
