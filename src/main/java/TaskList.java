import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void delete(int n) {
        this.tasks.remove(n);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void mark(int number) {
        tasks.get(number).markAsDone();
    }

    public void unmark(int number) {
        tasks.get(number).markAsUndone();
    }
}
