package spike.task;

import java.util.ArrayList;

/**
 * Stores all the task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getListSize() {
        return this.getTasks().size();
    }
}
