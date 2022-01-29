import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> read) {
        this.tasks = new ArrayList<>(read);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void removeTask(int idx) {
        tasks.remove(idx);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        if (!tasks.isEmpty()) {
            StringBuilder out = new StringBuilder();
            tasks.forEach(task -> out.append("\t").append(task.toString()).append("\n"));
            return out.toString();
        } else {
            return "\tEmpty list\n";
        }
    }
}