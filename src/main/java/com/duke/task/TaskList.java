package com.duke.task;

import com.duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;
    private int count;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.count = list.size();
    }

    public void add(Task task) {
        list.add(task);
        count++;
    }

    public Task remove (int index) {
        Task ts = list.remove(index);
        count--;
        return ts;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int getCount() {
        return count;
    }
}
