package main.java.duke.data;

import main.java.duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    
    public TaskList() {
        tasks = new ArrayList<>();
    }
    
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }
    
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public int taskLength() {
        return tasks.size();
    }
    
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
    
    public void markTask(int index) {
        tasks.get(index).setMark(true);
    }

    public void unmarkTask(int index) {
        tasks.get(index).setMark(false);
    }
    
}
