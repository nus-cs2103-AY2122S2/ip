package duke.task;

import java.util.ArrayList;

/**
 * Container class to hold a list of tasks
 */
public class TaskList {
    private ArrayList<Task> arr;

    public TaskList() {
        this.arr = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return arr;
    }

    /**
     * Returns the Task that is located at the highest index
     *
     * @return Task located at last index
     */
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

    public int getSize() {
        return this.arr.size();
    }
}
