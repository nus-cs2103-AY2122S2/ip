package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskMaster {

    ArrayList<Task> tasks;

    public TaskMaster(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task deleteTask(int i) {
        return this.tasks.remove(i - 1); //-1 because arr index starts frm 0
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDetails().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
