import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String str) {
        Task newTask = new Task(str);
        tasks.add(newTask);
    }

    public String getAllTasks() {
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            String item = (i + 1 + ". " + tasks.get(i) + "\n");
            strBuilder.append(item);
        }
        return strBuilder.toString();
    }
}
