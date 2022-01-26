package duke.tasklist;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNum) {
        taskList.remove(taskNum);
    }

    public Task markTask(int taskNum) {
        Task currTask = taskList.get(taskNum - 1);
        currTask.setChecked();
        return currTask;
    }

    public Task unmarkTask(int taskNum) {
        Task currTask = taskList.get(taskNum - 1);
        currTask.setUnchecked();
        return currTask;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
