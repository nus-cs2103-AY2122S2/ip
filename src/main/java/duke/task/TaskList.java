package duke.task;

import java.util.ArrayList;

public class TaskList {
    // contain tasks in array
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task markTask(int i) {
        tasks.get(i).markAsDone();
        return tasks.get(i);
    }

    public Task unmarkTask(int i) {
        tasks.get(i).markAsUndone();
        return tasks.get(i);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int i) {
        Task t = tasks.get(i - 1);
        tasks.remove(i - 1);
        return t;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int listSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append("    ");
            s.append(i + 1).append(". ");
            s.append(tasks.get(i));
            // no new line after last task
            if (i + 1 < tasks.size()) {
                s.append("\n");
            }
        }
        return s.toString();
    }
}
