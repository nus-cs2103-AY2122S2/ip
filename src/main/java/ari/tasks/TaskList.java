package main.java.ari.tasks;

import java.util.ArrayList;

public class TaskList { // contains the task list
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markTask(int index) {
        Task task = getTask(index);
        task.markDone();
    }

    public void unmarkTask(int index) {
        Task task = getTask(index);
        task.markNotDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
