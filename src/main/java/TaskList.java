import java.util.ArrayList;

public class TaskList {
    protected ArrayList<String> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<String> tasks) {
        this.tasks = tasks;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }
}
