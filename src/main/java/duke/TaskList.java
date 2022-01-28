package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task removeTaskIndex(int index) {
        return this.tasks.remove(index);
    }

    public Boolean removeTask(Task task) {
        return this.tasks.remove(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }
}