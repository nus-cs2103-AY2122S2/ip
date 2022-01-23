import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TaskStore {
    private ArrayList<Task> tasks;
    private ArrayList<Consumer<TaskStore>> changeListeners;

    public TaskStore() {
        this.tasks = new ArrayList<>();
        this.changeListeners = new ArrayList<>();
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        this.notifyListeners();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getTaskCount() {
        return this.tasks.size();
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

    public Task deleteTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            return null;
        }
        Task deleted = this.tasks.remove(index);
        this.notifyListeners();
        return deleted;
    }

    public void registerListener(Consumer<TaskStore> listener) {
        changeListeners.add(listener);
    }

    public void removeListener(Consumer<TaskStore> listener) {
        changeListeners.remove(listener);
    }

    public void notifyListeners() {
        this.changeListeners.forEach(handler -> {
            handler.accept(this);
        });
    }
}
