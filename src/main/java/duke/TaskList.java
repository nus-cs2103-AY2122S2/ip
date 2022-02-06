package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void add(Task t) {
        tasks.add(t);
    }

    int size() {
        return tasks.size();
    }

    Task get(int index) {
        return tasks.get(index);
    }

    void remove(int index) {
        tasks.remove(index);
    }
}
