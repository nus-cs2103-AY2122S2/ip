import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    private void addTask(Task t) {
        this.tasks.add(t);
    }

    private void deleteTask(Task t) {
        this.tasks.remove(t);
    }
}
