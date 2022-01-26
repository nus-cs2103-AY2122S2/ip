import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        // TODO
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void removeTask(int index) {
        if (index >= 0 && index <= tasks.size()) {
            tasks.remove(index);
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }
}
