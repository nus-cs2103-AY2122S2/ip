package siri;

import java.util.ArrayList;

//TaskList.java reused and edited from Brigette Santoso E0564307
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
