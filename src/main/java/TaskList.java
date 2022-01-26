import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int pos) {
        this.tasks.remove(pos);
    }

    public void markTask(int pos) {
        this.tasks.get(pos).mark();
    }

    public void unmarkTask(int pos) {
        this.tasks.get(pos).unmark();
    }

    public Task getTask(int pos) {
        return this.tasks.get(pos);
    }

    public int size() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
