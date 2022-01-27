package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskNotDone(int index) {
        tasks.get(index).markAsNotDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> findTasks(String word) {
        List<Task> tempTasks = new ArrayList<>();
        for (int i = 0 ;i < tasks.size();i++) {
            if (tasks.get(i).getDescription().contains(word)) {
                tempTasks.add(tasks.get(i));
            }
        }
        return tempTasks;
    }

}
