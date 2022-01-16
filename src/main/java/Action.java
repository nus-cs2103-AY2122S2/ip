public class Action {
    private final String act;
    private boolean isDone;

    public Action(String string){
         act = string;
         isDone = false;
    }

    public Action(String string, boolean status) {
        act = string;
        isDone = status;
    }

    public String getAction() {
        String head = act.split(" ")[0];
        return head;
    }

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

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + act;
    }
}
