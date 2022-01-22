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

    public String getFull() {
        return act;
    }

    /**
     * Returns the first word of the input
     * which is the action
     * @return head of message/the action
     */
    public String getAction() {
        String head = act.split(" ")[0];
        return head;
    }

    /**
     * Returns the input without the head
     * @return the body of the input
     */
    public String getBody() {
        String bod = act.split(" ", 2)[1];
        return bod;
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

    public boolean known() {
        return isList() || isMark() || isUnmark() || isDeadline() || isEvent()
                || isTodo() || isDelete();
    }

    public boolean isBye() {
        return act.equals("bye");
    }

    public boolean isList() {
        return act.equals("list");
    }

    public boolean isMark() {
        return getAction().contains("mark");
    }

    public boolean isUnmark() {
        return getAction().equals("unmark");
    }

    public boolean isDeadline() {
        return getAction().equals(("deadline"));
    }

    public boolean isEvent() {
        return getAction().equals("event");
    }

    public boolean isTodo() {
        return getAction().equals("todo");
    }

    public boolean isDelete() {
        return getAction().equals("delete");
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + act;
    }
}