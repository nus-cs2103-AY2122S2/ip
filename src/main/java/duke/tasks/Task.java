package duke.tasks;

/**
 * The abstract Task class contains basic attributes
 * and behaviours of a Task.
 *
 * @author  Melvin Chan Zijun
 */
public abstract class Task {
    /**
     * Name of the Task.
     */
    private final String name;

    /**
     * Whether the task is complete. Set to false by default
     */
    private boolean isCompleted;

    /**
     * Sole constructor.
     *
     * @param name - name of task
     */
    Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Abstract method that returns prefix of the Task
     *
     * @return String prefix of task
     */
    public abstract String getPrefix();

    /**
     * Abstract method that returns date of the Task
     *
     * @return String date of task
     */
    public abstract String getDate();

    /**
     * Abstract method that returns time of the Task
     *
     * @return String time of task
     */
    public abstract String getTime();

    /**
     * Updates the completion state of the Task to true.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Updates the completion state of the Task to false.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns the completion state of the Task.
     *
     * @return boolean completion state of task
     */
    public boolean isMarked() {
        return this.isCompleted;
    }

    /**
     * Returns true if task is an EmptyTask.
     * Otherwise, always return false.
     *
     * @return boolean false unless this is an EmptyTask,
     *                 otherwise true
     */
    public boolean isEmptyTask() {
        return false;
    }

    /**
     * Returns the name of the Task.
     *
     * @return String name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Overrides the default toString() method.
     *
     * @return String task in String form
     */
    @Override
    public String toString() {
        String box = "";
        if (this.isCompleted) {
            box += "[X] ";
        } else {
            box += "[ ] ";
        }
        return box + this.name;
    }

}
