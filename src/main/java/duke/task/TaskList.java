package duke.task;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final ArrayList<Consumer<TaskList>> changeListeners;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.changeListeners = new ArrayList<>();
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        this.notifyListeners();
        return task;
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public void doForEach(BiConsumer<Integer, ? super Task> consumer) {
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
        final Task deletedTask = this.tasks.remove(index);
        this.notifyListeners();
        return deletedTask;
    }

    public Task markTask(Task task, boolean isDone) {
        if (task.isDone() != isDone) {
            task.setDone(isDone);
            this.notifyListeners();
        }
        return task;
    }

    public void registerListener(Consumer<TaskList> listener) {
        this.changeListeners.add(listener);
    }

    public void removeListener(Consumer<TaskList> listener) {
        this.changeListeners.remove(listener);
    }

    private void notifyListeners() {
        this.changeListeners.forEach(handler -> {
            handler.accept(this);
        });
    }
}
