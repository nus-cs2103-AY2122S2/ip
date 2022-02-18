package duke.task;

/**
 * Represents an event created by the user that are/will be stored in the database.
 * An <code>Events</code> inherits from <code>Tasks</code> and is represented by its
 * name, completion status, and timing of the event e.g.,
 * <code>"Eat lunch", true, "9am"</code>
 */
public class Events extends Tasks {
    //Printing Format Constants
    public static final String DATAFORMAT_EVENTS = "E |";
    public static final String DATACOMPLETED_TASK = " X | ";
    public static final String DATAINCOMPLETED_TASK = "   | ";

    //Database Format Constants
    public static final String PRINTFORMAT_EVENTS = "[E]";
    public static final String PRINT_COMPLETEDTASK = "[X] ";
    public static final String PRINT_INCOMPLETEDTASK = "[ ] ";

    private final String timing; // Timing of event

    /**
     * ne of the two sole constructors of Events.
     *
     * @param taskName The name of the event.
     * @param timing The deadline of the event.
     */
    public Events(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    /**
     * One of the two sole constructors of Deadlines.
     *
     * @param taskName The name of the task.
     * @param completion The completion status of the task.
     * @param timing The deadline of the task.
     */
    public Events(String taskName, boolean completion, String timing) {
        super(taskName, completion);
        this.timing = timing;
    }

    public String getTiming() {
        return "(at: " + timing + ")";
    }

    /**
     * Complete a task
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Events completeTask() {
        return new Events(super.getName(), true, timing);
    }

    /**
     * Uncomplete the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Events uncompleteTask() {
        return new Events(super.getName(), false, timing);
    }

    /**
     * Present a database format of the task.
     *
     * @return A String value of the format the task uses to be saved in a database.
     */
    public String toDatabaseString() {
        return DATAFORMAT_EVENTS + (this.getCompletion()
                ? DATACOMPLETED_TASK : DATAINCOMPLETED_TASK) + super.getName()
                + " | " + timing;
    }

    /**
     * Present a print format of the task.
     *
     * @return A String value of the format when printed.
     */
    public String toString() {
        return PRINTFORMAT_EVENTS + (super.getCompletion()
                ? PRINT_COMPLETEDTASK : PRINT_INCOMPLETEDTASK) + super.getName()
                + " (At: " + timing + ") " + "";
    }
}
