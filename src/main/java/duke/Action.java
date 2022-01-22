package duke;

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
     * Returns the first word of the input
     * which is the action
     * @return head of message/the action
     */
    public String getAction() {
        return act.split(" ")[0];
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public Action setDone() {
        return new Action(act, true);
    }

    public Action setUnDone() {
        return new Action(act, false);
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + act;
    }
}