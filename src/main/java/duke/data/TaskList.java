package main.java.duke.data;

import main.java.duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskLists;
    
    public TaskList() {
        taskLists = new ArrayList<>();
    }
    
    public void addTask(Task newTask) {
        taskLists.add(newTask);
    }
    
    public Task removeTask(int index) {
        return taskLists.remove(index);
    }

    public Task getTask(int index) {
        return taskLists.get(index);
    }
    
    public int taskLength() {
        return taskLists.size();
    }
    
    public ArrayList<Task> getTaskList() {
        return this.taskLists;
    }
    
    public void markTask(int index) {
        taskLists.get(index).setMark(true);
    }

    public void unmarkTask(int index) {
        taskLists.get(index).setMark(false);
    }
    
}
