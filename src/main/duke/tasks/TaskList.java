package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setDone(true);
    }

    public void unmarkTask(int index) {
        tasks.get(index).setDone(false);
    }

    public String taskListToString() {
        StringBuilder result = new StringBuilder();
        String title = tasks.isEmpty() ? "You got no task now! Start by adding new tasks.\n"
                : "Here are the task(s) in your list:\n";
        result.append(title);
        for (int i = 0; i < tasks.size(); ++i) {
            int index = i + 1;
            result.append("  " + index + ". " + tasks.get(i).toString() + "\n");
        }
        return result.toString();
    }
}
