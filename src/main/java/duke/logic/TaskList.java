package duke.logic;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void clear() {
        this.tasks.clear();
    }

    public int size() {
        return this.tasks.size();
    }

    public String toData() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.tasks) {
            data.append(task.toData()).append("\n");
        }
        return data.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return result.toString();
    }
}
