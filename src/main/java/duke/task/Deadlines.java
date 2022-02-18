package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline created by the user that are/will be stored in the database.
 * A Deadlines inherits from Tasks and is represented by its
 * name, completion status, and the deadline of the event e.g.,
 * "Eat lunch", true, "2012-06-03"
 */
public class Deadlines extends Tasks {
    //Printing Format Constants
    public static final String DATAFORMAT_DEADLINE = "D |";
    public static final String DATA_COMPLETEDTASK = " X | ";
    public static final String DATA_INCOMPLETEDTASK = "   | ";

    //Database Format Constants
    public static final String PRINTFORMAT_DEADLINE = "[D]";
    public static final String PRINT_COMPLETEDTASK = "[X] ";
    public static final String PRINT_INCOMPLETEDTASK = "[ ] ";

    private final LocalDate deadline; // Deadline to complete deadline duke.task

    /**
     * One of the two sole constructors of Deadlines.
     *
     * @param taskName The name of the task.
     * @param deadline The deadline of the task.
     * @throws DateTimeParseException if an invalid DateTime value is parsed.
     */
    public Deadlines(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * One of the two sole constructors of Deadlines.
     *
     * @param taskName The name of the task.
     * @param completion The completion status of the task.
     * @param deadline The deadline of the task.
     * @throws DateTimeParseException if an invalid DateTime value is parsed.
     */
    public Deadlines(String taskName, boolean completion, String deadline) throws DateTimeParseException {
        super(taskName, completion);
        this.deadline = LocalDate.parse(deadline);
    }

    public String getTiming() {
        return "(by: " + deadline + ")";
    }

    /**
     * Complete a task.
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Deadlines completeTask() {
        return new Deadlines(super.getName(), true,
                deadline.toString());
    }

    /**
     * Uncomplete the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Deadlines uncompleteTask() {
        return new Deadlines(super.getName(), false,
                deadline.toString());
    }

    /**
     * Present a database format of the task.
     *
     * @return A String value of the format the task uses to be saved in a database.
     */
    public String toDatabaseString() {
        return DATAFORMAT_DEADLINE + (this.getCompletion()
                ? DATA_COMPLETEDTASK : DATA_INCOMPLETEDTASK) + super.getName()
                + " | " + deadline;
    }

    /**
     * Present a print format of the task.
     *
     * @return A String value of the format when printed.
     */
    public String toString() {
        return PRINTFORMAT_DEADLINE + (this.getCompletion()
                ? PRINT_COMPLETEDTASK : PRINT_INCOMPLETEDTASK) + super.getName()
                + " (By: " + deadline + ")";
    }

}
