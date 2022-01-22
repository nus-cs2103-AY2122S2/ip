import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(String task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public String get(int i) {
        return taskList.get(i);
    }
}
