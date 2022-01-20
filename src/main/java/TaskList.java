import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> todo;

    public TaskList() {
        this.todo = new ArrayList<>();
    }

    public void addItem(Task item) {
        todo.add(item);
    }

    @Override
    public String toString() {
        String wrapee = "";
        for (int i = 0; i < todo.size(); i++) {
            wrapee = wrapee + (i + 1) + ". " + todo.get(i) + "\n";
        }
        return wrapee;
    }

    public String markFinished(int num) {
        todo.get(num - 1).finished();
        return Response.MARKDONE + "\n" +  todo.get(num - 1).toString();
    }

    public String unmarkFinished(int num) {
        todo.get(num - 1).notFinished();
        return Response.MARKDONE + "\n" +  todo.get(num - 1).toString();
    }

    public int size() {
        return this.todo.size();
    }
}
