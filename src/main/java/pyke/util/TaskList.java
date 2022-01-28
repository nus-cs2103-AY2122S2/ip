package pyke.util;

import pyke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public int getSize() {
        return taskList.size();
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public String getTaskOutputStyle(int taskId) {
        return taskList.get(taskId).toString();
    }

    public String getTaskSavingStyle(int taskId) {
        return taskList.get(taskId).toSavedFile();
    }

    public Task addTask(Task newTask) {
        taskList.add(newTask);
        return newTask;
    }

    public Task delTask(int taskId) {
        Task temp = taskList.get(taskId);
        taskList.remove(taskId);
        return temp;
    }

    public Task setTaskStatus(int taskId, boolean newStatus) {
        Task temp = taskList.get(taskId);
        temp.setStatus(newStatus);
        return temp;
    }

}
