package duke.component;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
      tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTaskByIndex(int index) {
        tasks.remove(index);
    }

    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

}
