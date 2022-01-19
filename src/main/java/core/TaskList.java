package core;

import utilities.OutputFormatter;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    private TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> initialList) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(initialList);
    }

    public static TaskList getInstance() {
        return new TaskList();
    }

    public static TaskList getInstance(ArrayList<Task> initialList) {
        return new TaskList(initialList);
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTaskByIndex(int index) {
        return this.taskList.get(index);
    }

    public Task getTaskByTaskId(int id) {
        for (Task task : taskList) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    public void completeTaskByIndex(int index) {
        this.taskList.get(index).complete();
    }

    public void completeTaskByTaskId(int taskId) {
        getTaskByTaskId(taskId).complete();
    }

    public String formattedOutput() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("Here are the tasks in your list: ", "\n");

        for (Task task : this.taskList) {
            outputFormatter.appendAll(task.getTaskId(), ".[", task.status(), "] ", task.getTaskDescription());

            if (task.getTaskId() < taskList.size()) {
                outputFormatter.append("\n");
            }
        }
        return outputFormatter.getFormattedOutput();
    }

}
