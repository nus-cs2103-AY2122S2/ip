package Tasks;

/**
 * <h1>Task</h1>
 * <p>
 * Tasks contains the operations that can be performed on task.
 * </p>
 *
 * @author Saravanan Anuja Harish
 * This file contains the implementation of Task class.
 */
public class Task {

    // symbol for indicating task is completed.
    private static final String CROSS = "X";

    // symbol for indicating task is yet to be completed.
    private static final String SPACE = " ";

    // symbol for task done.
    private static final String DONE = "[X]";

    // symbol for task not done.
    private static final String NOT_DONE = "[ ]";

    // stores the start index value.
    private static final int START_INDEX = 0;

    // task variable stores the task.
    private final String task;

    // Tells if the task is completed.
    private boolean done;

    /**
     * Constructor for Task.
     * @param task the task to be done.
     * returns a new instance of task.
     */
    public Task(String task) {
        this.task = task.trim();
        this.done = false;
    }

    /**
     * Constructor for Task.
     * @param task the string output of Task.
     * @param dummyVariable int to differentiate from other constructor.
     * returns a new instance of Task.
     */
    public Task(String task, int dummyVariable) {

        if (task.substring(START_INDEX, DONE.length()).contains(DONE)) {
            this.done = true;
        } else {
            this.done = false;
        }

        this.task = task.substring(DONE.length()).trim();

    }

    /**
     * markDone marks the task completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * markDone unmarks the task as not completed.
     */
    public void unMarkDone() {
        this.done = false;
    }

    /**
     * returns the task to be performed.
     * @return the task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * checks if the task is due before date.
     * @param date the string given by user.
     * @return false trivially, as Tasks do not have deadlines, or event date.
     */
    public boolean isBefore(String date) {
        return false;
    }

    /**
     * checks if the task is on date.
     * @param date the string given by the user
     * @return false trivially, as tasks do not have dates associated with them.
     */
    public boolean isOnDate(String date) {
        return false;
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
