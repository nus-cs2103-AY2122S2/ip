package duke.task;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Container class to hold a list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Returns the Task that is located at the highest index
     *
     * @return Task located at last index
     */
    public Task getLast() {
        return tasks.get(tasks.size() - 1);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int index) {
        tasks.remove(index);
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
        return this.tasks.size();
    }
}
