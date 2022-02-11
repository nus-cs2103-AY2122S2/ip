package duke;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskManager() { }
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public boolean deleteTask(Task t) {
        return tasks.remove(t);
    }

    public boolean deleteTask(int index) {
        return tasks.remove(index) != null;
    }

    /**
     * Marks a task as done in the current task list.
     *
     * @param index The index of the task in this task list.
     * @return True if the task is successfully marked, false otherwise.
     */
    public boolean markTaskDone(int index) {
        if (tasks.size() <= 0) {
            return false;
        } else {
            if (index < 0 || index >= tasks.size()) {
                return false;
            } else {
                Task t = tasks.get(index);
                return t.markDone();
            }
        }
    }

    /**
     * Marks a task as undone in the current task list.
     *
     * @param index The index of the task in this task list.
     * @return True if the task is successfully unmarked, false otherwise.
     */
    public boolean markTaskUndone(int index) {
        if (tasks.size() <= 0) {
            return false;
        } else {
            if (index < 0 || index >= tasks.size()) {
                return false;
            } else {
                Task t = tasks.get(index);
                return t.markUndone();
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
    public Task getTask(int index) {
        if (size() == 0) {
            return null;
        }

        if (index < 0 || index >= size()) {
            return null;
        }

        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }
}
