package main.duke;

import main.duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> Tasks;

    public TaskList() {
        this.Tasks = new ArrayList<>();
    }

    public Task getTask(int taskIndex) {
        return this.Tasks.get(taskIndex);
    }

    public void addTask(Task newTask) {
        this.Tasks.add(newTask);
    }

    public void deleteTask(int taskIndex) {
        this.Tasks.remove(taskIndex);
    }

    public int getTasksCount() {
        return this.Tasks.size();
    }

    public String taskCountToString() {
        return String.format("Now you have %d task(s) in the list.", this.getTasksCount());
    }
}
