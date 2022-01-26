package duke.tasks;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task task) {
        this.listOfTasks.add(task);
    }

    public void deleteTask(int n) {
        this.listOfTasks.remove(n);
    }

    public void markTask(int n) {
        this.getTask(n).mark();
    }

    public void unMarkTask(int n) {
        this.getTask(n).unMark();
    }

    public Task getTask(int n) {
        return this.listOfTasks.get(n);
    }

    public int getSize() {
        return this.listOfTasks.size();
    }
}
