package duke.task;

/**
 * Represents a task created by the user that are/will be stored in the database.
 * A <code>Tasks</code> is represented by its name and completion status e.g.,
 * <code>"Eat breakfast by 9am", true</code>
 */
public class Tasks {
    private final String name; // Name of duke.task
    private final boolean hasCompleted; // Completion of duke.task

    // Constructor for Task
    /**
     * One of the two sole constructors of a Task.
     *
     * @param taskName The name of the task.
     */
    public Tasks(String taskName) {
        this.name = taskName;
        this.hasCompleted = false;
    }

    /**
     * One of the two sole constructors of Deadlines.
     *
     * @param taskName The name of the task.
     * @param completion The completion status of the task.
     */
    public Tasks(String taskName, boolean completion) {
        this.name = taskName;
        this.hasCompleted = completion;
    }

    /**
     * Complete a task.
     *
     * @return a new instance of the task that has been completed.
     */
    public Tasks completeTask() {
        return new Tasks(name, true);
    }

    /**
     * Uncomplete the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    public Tasks uncompleteTask() {
        return new Tasks(name, false);
    }

    public String getTiming() {
        return "";
    }

    public String getDeadline() {
        return "";
    }

    public String getName() {
        return this.name;
    }

    Boolean getCompletion() {
        return this.hasCompleted;
    }

    /**
     * Present a database format of the task.
     *
     * @return A String value of the format the task uses to be saved in a database.
     */
    public String toDatabaseString() {
        return "";
    }

    /**
     * Present a print format of the task.
     *
     * @return A String value of the format when printed.
     */
    public String toString() {
        return "";
    }
}
