import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }
}
