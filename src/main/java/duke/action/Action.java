package java.duke.action;

public class Action {

    protected final String act;
    protected final boolean isDone;

    public Action(String string) {
         act = string;
         isDone = false;
    }

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
     * Returns a new Action object signifiying that
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

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + act;
    }
}