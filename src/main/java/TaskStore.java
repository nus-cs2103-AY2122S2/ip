import java.util.ArrayList;
import java.util.function.BiConsumer;

public class TaskStore {
    private ArrayList<Task> tasks;

    public TaskStore() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void forEach(BiConsumer<Integer, ? super Task> consumer) {
        for (int i = 0; i < this.tasks.size(); i++) {
            consumer.accept(i, this.tasks.get(i));
        }
    }

    public Task getTaskByIndex(int index) {
        if (index >= this.tasks.size() || index < 0) {
            return null;
        }
        return this.tasks.get(index);
    }
}
