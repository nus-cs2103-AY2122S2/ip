package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskArr = new ArrayList<>();

    public TaskList() {
        taskArr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    public Task get(int i) {
        return taskArr.get(i);
    }

    public void add(Task task) {
        taskArr.add(task);
    }

    public void remove(Task task) {
         taskArr.remove(task);
    }

    public int size() {
        return taskArr.size();
    }

    public boolean isEmpty() {
        return taskArr.isEmpty();
    }
}
