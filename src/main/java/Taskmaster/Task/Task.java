package Taskmaster.Task;


/*
 * Task encapsulates the information necessary for a user task
 */


public class Task {
    protected String taskName;
    protected Boolean completed;

    /**
     * Constructor for Task Objects
     *
     * @param taskName - The name/description of the task
     */

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    /**
     *  Marks the task as completed
     *
     */

    public void markTask() {
        this.completed = true;
    }

    /**
     *  Marks a completed task as incomplete
     *
     */

    public void unmarkTask() {
        this.completed = false;
    }

    /**
     *  Format the string representation of task objects
     *
     * @return String representation of task objects
     */


    @Override
    public String toString() {
        String completedOrNah = this.completed? "X":"";
         return "[" + completedOrNah + "] " + taskName;

    }

    /**
     * Save format in the hard disk
     * For Overriding purposes only
     */
    public String saveToFileFormat() {
        return taskName;
    }

    public boolean containsKeyword(String keyword) {
            return this.taskName.contains(keyword);
    }




}