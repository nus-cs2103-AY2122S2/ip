import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i+1).append(".").append(tasks.get(i)).append("\n");
        }
        return tasks.size() == 0 ? "Task list is empty." : sb.toString();
    }
}
