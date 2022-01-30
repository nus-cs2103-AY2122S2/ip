package duke;
import duke.Task.Task;

import java.util.ArrayList;

/**
 * A class that stores a lot of tasks as one array
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void add(String description) {
        this.taskList.add(new Task(description));
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public void markAsDone(int index) {
        this.taskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        this.taskList.get(index).markAsUndone();
    }
}
