package mcbot;

import java.util.ArrayList;
import mcbot.task.Task;

public class TaskList {
    
    private ArrayList<Task> taskList;
    
    public TaskList() {
    }
    
    public TaskList(ArrayList<Task> arrayList) {
        taskList = arrayList;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void remove(int i) {
        taskList.remove(i);
    }
}
