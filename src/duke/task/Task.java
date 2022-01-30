package task;

import java.util.HashMap;

/**
 * Represent a task that is stored by main.Duke.
 */
public abstract class Task {

    private boolean isDone;
    private String description;

    // Used for generating info table about the task.
    private static final String DESCRIPTION_FIELD = "description";
    private static final String IS_DONE_FILED = "is_done";
    private static final String TASK_TYPE_FIELD = "task_type";

    /**
     * Constructor of a task. Sets the description of the task, and set the isDone status to be false by default.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor using an infoTable.
     *
     * @param infoTable The <code>HashMap</code> that contains the information about the current task.
     */
    protected Task(HashMap<String, Object> infoTable) {

        this.description = (String) infoTable.get(DESCRIPTION_FIELD);
        this.isDone = (boolean) infoTable.get(IS_DONE_FILED);
    }

    /**
     * Factory method. Parses the instruction and determines the type of task to be instantiated.
     *
     * @param instruction The line of instruction used to create a task.
     * @return The instantiated task.
     * @throws FailedToInterpretTaskException If the instruction (i) does not contain valid type of task; or
     * (ii) does not contain a valid description of task.
     */
    public static Task of(String instruction) throws FailedToInterpretTaskException {

        String[] args = instruction.split(" ", 2);
        String type = args[0];

        if (args.length < 2) {

            throw new FailedToInterpretTaskException("Oops, a type and a description for the task must be provided.");
        }

        // Contains the description and possibly other information about the task.
        String details = args[1];

        switch (type) {

            case "todo":
                return new ToDo(details);
            case "event":
                return new Event(details);
            case "deadline":
                return new Deadline(details);
            default:
                // Invalid type.
                throw new IllegalArgumentException("Oops, the type of the task must be todo/event/deadline.");
        }
    }


    /**
     * Marks the current task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done.
     */
    protected void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Obtains the status of the task.
     *
     * @return True if the task is done already, false otherwise.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description;
    }

    /**
     * Gets the icon that represents the status of the task.
     *
     * @return The icon.
     */
    protected String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    protected abstract String getTypeIcon();

    /**
     * Returns the type of the current task.
     *
     * @return The type of the task.
     */
    protected abstract TaskType getType();

    /**
     * Turns the current <code>task.Task</code> into a <code>HashMap</code> that contains the necessary information to be
     * written into hard disk.
     *
     * @return The HashMap containing the information about the current <code>task.Task</code>.
     */
    public abstract HashMap<String, Object> getInfoTable();

    /**
     * Initializes the information table for the current task. This table contains the (attribute, value) for all the
     * common attributes that a <code>task.Task</code> contains.
     *
     * @return
     */
    protected HashMap<String, Object> initializeInfoTable() {

        HashMap<String, Object> infoTable = new HashMap<>();
        infoTable.put(TASK_TYPE_FIELD, this.getType());
        infoTable.put(DESCRIPTION_FIELD, this.description);
        infoTable.put(IS_DONE_FILED, this.isDone);

        return infoTable;
    }

    /**
     * Retrieves a <code>task.Task</code> from a hash table that contains the relevant data.
     *
     * @param infoTable The <code>HashMap</code> that contains the data of a <code>task.Task</code>.
     * @return The task obtained.
     */
    public static Task retrieveTask(HashMap<String, Object> infoTable) throws TaskNotFoundException {

        TaskType taskType = (TaskType) infoTable.get(TASK_TYPE_FIELD);

        switch (taskType) {

        case DEADLINE:
            return new Deadline(infoTable);
        case TODO:
            return new ToDo(infoTable);
        case EVENT:
            return new Event(infoTable);
        }


        throw new TaskNotFoundException();
    }


}
