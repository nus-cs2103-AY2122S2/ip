package task;

import java.util.ArrayList;

public class TaskList { //figure out how to process existing taskList from storage
    ArrayList<Task> tasks;

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

    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += (i + 1 + ". " + tasks.get(i) + "\n");
        }
        return s.trim();
    }

}
