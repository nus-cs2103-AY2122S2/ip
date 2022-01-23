package duke.task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;

    public TaskList() {
        this.arr = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return arr;
    }

    public Task getLast() {
        return arr.get(arr.size() - 1);
    }

    public Task getTask(int index) {
        return arr.get(index);
    }

    public void addTask(Task t) {
        arr.add(t);
    }

    public void removeTask(int index) {
        arr.remove(index);
    }

    public ArrayList<Task> findTasksContaining(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for(Task t : arr) {
            if(t.getTaskName().contains(keyword)) {
                result.add(t);
            }
        }
        return result;
    }

    public int getSize() {
        return this.arr.size();
    }
}
