package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> savedTasks) {
        this.tasks = savedTasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void deleteTask(Task t) {
        this.tasks.remove(t);
    }

    public String getTasksMsg() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.toString()).append("\n");
        }
        return s.toString();
    }

    public String getTaskStore() {
        StringBuilder s = new StringBuilder();
        for (Task t: this.tasks) {
            s.append(t.writeToFile()).append("\n");
        }
        return s.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
}
