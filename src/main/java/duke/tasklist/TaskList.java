package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> arr) {
        this.tasks = arr;
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int getCount() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

}
