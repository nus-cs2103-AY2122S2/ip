package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int n) {
        return taskList.get(n);
    }

    public Task remove(int n) {
        return taskList.remove((n));
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
