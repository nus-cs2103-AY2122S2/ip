package bobby.task;

import java.util.ArrayList;
import java.util.Collections;

import bobby.exception.BobbyException;
import bobby.exception.InvalidNumberException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(Task t) {
        tasks.remove(t);
    }

    public void removeAll() {
        tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getIndex(int index) throws BobbyException {
        Task resTask;
        if (index < 0) {
            throw new InvalidNumberException("negative");
        }
        try {
            resTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidNumberException("OOB");
        }
        return resTask;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void sortTaskList() {
        if (tasks.isEmpty()) {
            return;
        }
        Collections.sort(tasks);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
