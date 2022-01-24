package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task toggleCompleted(boolean isMark, int index) {
        Task updateTask = this.tasks.get(index);

        updateTask.setCompleted(isMark);

        return updateTask;
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }
}
