package duke.task;

/**
 * Represents a task created by the user that are/will be stored in the database.
 * A <code>Tasks</code> is represented by its name and completion status e.g.,
 * <code>"Eat breakfast by 9am", true</code>
 */
public class Tasks {
    String name; // Name of duke.task
    boolean hasCompleted; // Completion of duke.task

    // Constructor for Task
    public Tasks(String taskName) {
        this.name = taskName;
        this.hasCompleted = false;
    }

    public Tasks(String taskName, boolean completion) {
        this.name = taskName;
        this.hasCompleted = completion;
    }

    /**
     * Returns a new completed instance of the task.
     *
     * @return a new instance of the task that has been completed.
     */
    public Tasks completeTask() {
        return new Tasks(name, true);
    }

    /**
     * Returns a new uncompleted instance of the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    public Tasks uncompleteTask() {
        return new Tasks(name, false);
    }

    String getTiming() {
        return "";
    }

    // Get timing of duke.task - overriden in todos and events
    String getDeadline() {
        return "";
    }

    // Get name of duke.task
    String getName() {
        return this.name;
    }

    // Get completion status of the duke.task
    Boolean getCompletion() {
        return this.hasCompleted;
    }

    public String toDatabaseString() {
        return "";
    }

    // toString return tasks
    public String toString() {
        return "";
    }
}
