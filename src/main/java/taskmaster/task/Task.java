package taskmaster.task;
/**
 * This class encapsulates the information necessary for a user task.
 */
public abstract class Task {
    /** Name of the task. **/
    protected String taskName;

    /** Status of the task. **/
    protected boolean isCompleted;

    /**
     * Constructor for Task Objects.
     *
     * @param taskName - The name or description of the task.
     */

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     *  Marks the task as completed.
     *
     */

    public void markTask() {
        this.isCompleted = true;
    }

    /**
     *  Marks a task as incomplete.
     *
     */

    public void unmarkTask() {
        this.isCompleted = false;
    }

    /**
     * Format the string representation of task objects.
     *
     * @return String representation of task objects
     */


    @Override
    public String toString() {
        String isCompletedOrNah = this.isCompleted ? "X" : "";
        return "[" + isCompletedOrNah + "] " + taskName;
    }

    /**
     * Format the string representation of task objects for
     * saving and writing to the text file.
     */
    public abstract String saveToFileFormat();

    /**
     * Method to check whether Task Name/Description
     * contains the specified keyword.
     *
     * @param keyword Word to be checked.
     * @return True if keyword is present and false if otherwise.
     */

    public boolean containsKeyword(String keyword) {
        return this.taskName.contains(keyword);
    }
}
