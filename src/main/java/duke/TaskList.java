package duke;

import java.util.ArrayList;

/**
 * TaskList is a class that manages the storage of tasks inside the application
 */
public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    int size() {
        return tasks.size();
    }

    void add(Task task) {
        tasks.add(task);
    }

    Task get(int position) {
        return tasks.get(position);
    }

    void remove(int position) {
        tasks.remove(position);
    }
}
