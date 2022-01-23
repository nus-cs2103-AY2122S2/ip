import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private int taskListSize;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskListSize = 0;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public int getTaskListSize() {
        return this.taskListSize;
    }
}
