import java.util.ArrayList;
import java.util.List;

// contains the task list e.g., it has operations to add/delete tasks in the list
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public void clear() {
        tasks.clear();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(tasks);
    }
}
