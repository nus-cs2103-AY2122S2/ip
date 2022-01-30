package duke.action;

/**
 * Base class of all the task classes.
 * String variable act describes the task.
 * boolean variable isDone describes the status of the task.
 */
public class Action {

    protected final String act;
    protected final boolean isDone;

    /**
     * Constructs the Action class by taking in a
     * String variable, string. Sets the isDone variable
     * to false by default.
     * @param string task
     */
    public Action(String string) {
         act = string;
         isDone = false;
    }

    /**
     * Constructs the Action class by taking in a
     * String variable, string and a boolean
     * variable, status.
     * @param string task
     * @param status done status of task
     */
    public Action(String string, boolean status)  {
        act = string;
        isDone = status;
    }

    /**
     * Returns the mark status of the Action/Task:
     * An "X" if marked done, else a whitespace.
     * @return mark status
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the task
     * @return task
     */
    public String getTask() {
        return act;
    }

    /**
     * Returns a new Action object signifying that
     * it has been marked as done. The isDone variable
     * is now given a true value.
     * @return marked Action
     */
    public Action setDone() {
        return new Action(act, true);
    }

    /**
     * Returns a new Action object signifying that
     * it has been unmarked as not done. The isDone variable
     * is now given a false value.
     * @return unmarked Action
     */
    public Action setUnDone() {
        return new Action(act, false);
    }

    /**
     * Returns a String representation of this Action
     * object.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + act;
    }
}