package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        taskList = list;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int n) {
        this.taskList.remove(n);
    }

    public Task getTask(int n) {
        return this.taskList.get(n);
    }

    public int getSize() {
        return this.taskList.size();
    }
}
