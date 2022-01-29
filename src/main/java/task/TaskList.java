package task;

import java.util.ArrayList;

/**
 * TaskList represents the list of the tasks in the user's todo-list.
 * Functions below basically all apply to the ArrayList made in the constructor.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void delete(int serialNumber) {
        tasks.remove(serialNumber - 1);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1 + ". " + tasks.get(i) + "\n");
        }
        return s.trim();
    }

}
