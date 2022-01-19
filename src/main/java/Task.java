/**
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Task class.
 */


public class Task {

    // symbol for indicating task is completed.
    private static final String CROSS = "X";

    // symbol for indicating task is yet to be completed.
    private static final String SPACE = " ";

    // task variable stores the task.
    private final String task;
    // Tells if the task is completed.
    private boolean done;

    /**
     * Constructor for Task.
     * @param task the task to be done.
     * returns a new instance of task.
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * markDone marks the task completed.
     * @return the String representation of the instance.
     */
    String markDone() {
        this.done = true;
        return toString();
    }

    /**
     * markDone unmarks the task as not completed.
     * @return the String representation of the instance.
     */
    String unMarkDone() {
        this.done = false;
        return toString();
    }

    /**
     * returns the task to be performed.
     * @return the task.
     */
    String getTask() {
        return this.task;
    }

    /**
     * toSting method returns the string representation of the object.
     * @return the string representing the task instance.
     */
    @Override
    public String toString() {
        return "[" +  (this.done ? CROSS : SPACE ) + "] " + this.task;
    }

}
