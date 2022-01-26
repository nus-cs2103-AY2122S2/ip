import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(Task t) {
        tasks.remove(t);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void sortTaskList() {
        Collections.sort(tasks);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }
}
