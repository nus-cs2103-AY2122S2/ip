package duke.action;

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

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public String getTask() {
        return act;
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