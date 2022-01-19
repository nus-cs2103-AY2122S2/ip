package core;

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
        StringBuilder output = new StringBuilder();

        for (Task task : this.taskList) {
            output.append(task.getTaskId());
            output.append(": ");
            output.append(task.getTaskDescription());

            if (task.getTaskId() < taskList.size()) {
                output.append("\n");
            }
        }
        return output.toString();
    }

}
