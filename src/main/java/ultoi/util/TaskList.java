package ultoi.util;

import ultoi.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public void mark(int index) {
        getTask(index).markAsDone();
    }

    public void unmark(int index) {
        getTask(index).markAsUndone();
    }

    public String generateNumOfTasksMsg() {
        return "Now you have " + size() + " task(s) in total.";
    }

    public TaskList findTasksWith(String keyword) {
        List<Task> matchingTasks = new ArrayList<Task>();

        for (int i = 0; i < size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }

        return new TaskList(matchingTasks);
    }

    public String toInputString() {
        String str = "";
        for (int i = 0; i < size(); i++) {
            str = str + (i + 1) + ". " + getTask(i).toInputString();
            if (i < size() - 1) {
                str = str + "\n";
            }
        }
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size(); i++) {
            str = str + (i + 1) + ". " + getTask(i).toString();
            if (i < size() - 1) {
                str = str + "\n";
            }
        }
        return str;
    }
}
