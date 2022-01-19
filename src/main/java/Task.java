/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Task class.
 */


public class Task {

    // task variable stores the task
    private final String task;

    /**
     * Constructor for Task.
     * @param task the task to be done.
     * returns a new instance of task.
     */
    Task(String task) {
        this.task = task;
    }

    /**
     * toSting method returns the string representation of the object.
     * @return the string representing the task instance.
     */
    @Override
    public String toString() {
        return this.task;
    }

}
