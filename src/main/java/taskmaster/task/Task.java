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
        String markOrUnmark = this.isCompleted ? "X" : "";
        return "[" + markOrUnmark + "] " + taskName;
    }

    /**
     * Format the string representation of task objects for
     * saving and writing to the text file.
     *
     * @return string representation of how the file will be saved
     * in the data file.
     */
    public abstract String saveToFileFormat();

    /**
     * Method to check whether Task Name/Description
     * contains the specified keyword. The task name
     * is converted into lower case for the search to be
     * non-case sensitive.
     *
     * @param keyword Word to be checked.
     *
     * @return True if keyword is present and false if otherwise.
     */

    public boolean containsKeyword(String keyword) {
        return this.taskName.toLowerCase().contains(keyword);
    }


    public String addTag(String tag) {
        return "Successfully added tag: \n" + tag + " to " + taskName + "\n";
    }

}

