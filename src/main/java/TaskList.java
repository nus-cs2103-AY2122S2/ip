import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list = new ArrayList<>();

    public int size() {
        return this.list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(Task t) {
        list.remove(t);
    }
}
