package apollo.tasks;

import java.util.ArrayList;

public class TaskList implements java.io.Serializable{
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task deleteTask(int i) {
        Task toDelete = taskList.get(i);
        taskList.remove(i);
        return toDelete;
    }

    public Task markTask(int i, boolean isDone) {
        taskList.get(i).markAs(isDone);
        return taskList.get(i);
    }

    public int taskCount() {
        return taskList.size();
    }

    public String getTaskString(int i) {
        return taskList.get(i).toString();
    }
}
