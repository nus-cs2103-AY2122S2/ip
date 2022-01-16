public class Action {
    private final String act;

    public Action(String string){
         act = string;
    }

    public String getAction() {
        String head = act.split(" ")[0];
        return head;
    }

    public boolean isBye() {
        return act.equals("bye");
    }

    @Override
    public String toString() {
        return act;
    }
}
