package bobby.task;

import java.util.ArrayList;
import java.util.Collections;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
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

    public void removeAll() {
        tasks = new ArrayList<>();
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
        if (tasks.isEmpty()) {
            return;
        }
        Collections.sort(tasks);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
