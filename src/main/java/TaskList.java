import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public String getTaskStatement(int i) {
        return this.get(i).toString();
    }

}
