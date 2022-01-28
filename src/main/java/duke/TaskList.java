package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    public Task delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }
}
