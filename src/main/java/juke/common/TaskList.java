package juke.common;

import juke.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public TaskList(Collection<Task> tasks) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(tasks);
    }
    
    public boolean add(Task task) {
        return this.taskList.add(task);
    }
    
    public Task remove(int index) {
        return this.taskList.remove(index);
    }
    
    public Task get(int index) {
        return this.taskList.get(index);
    }
    
    public int size() {
        return this.taskList.size();
    }
    
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
