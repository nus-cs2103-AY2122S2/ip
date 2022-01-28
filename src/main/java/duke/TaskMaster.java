package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskMaster {

    ArrayList<Task> tasks;

    public TaskMaster(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add_task(Task t) {
        this.tasks.add(t);
    }

    public Task delete_task(int i) {
        Task removed_task = this.tasks.remove(i - 1); //-1 because arr index starts frm 0
        return removed_task;
    }

    public ArrayList<Task> get_tasks() {
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
