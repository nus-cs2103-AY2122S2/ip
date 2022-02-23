import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> tasks;

    public TaskList() {
        tasks = new ArrayList<>(0);
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                str.append("\n");
            }
            str.append(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return str.toString();
    }
}
