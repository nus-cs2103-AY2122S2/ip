package dukeclasses;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task updateTask(int index, boolean isDone) {
        if (isDone) {
            tasks.get(index).setDone(true);
        } else {
            tasks.get(index).setDone(false);
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

}
