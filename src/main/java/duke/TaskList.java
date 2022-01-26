package main.java.duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr;
    private int currTask;

    public TaskList() {
        this.taskArr = new ArrayList<Task>();
        currTask = 0;
    }

    public TaskList(ArrayList<Task> t) {
        this.taskArr = t;
        this.currTask = t.size();
    }

    public void mark(int num) {
        taskArr.get(num).markDone();
    }

    public void unmark(int num) {
        taskArr.get(num).markUndone();
    }


    public ArrayList<Task> getTaskArr() {
        return this.taskArr;
    }

    public void addTask(Task t) {
        taskArr.add(t);
        currTask++;
    }

    public void delete(int taskNum) {
        taskArr.remove(taskNum);
        currTask--;
    }

    public int size() {
        return taskArr.size();
    }

    public Task getTask(int num) {
        return taskArr.get(num);
    }
}
