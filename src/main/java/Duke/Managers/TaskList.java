package Duke.Managers;

import java.io.Serializable;
import java.util.ArrayList;
import Duke.Tasks.Task;
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }
}
