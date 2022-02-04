import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;
    private int taskListSize;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskListSize = 0;
    }

    public static void add(Task task) {
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

    public void remove(int numToDelete) {
        Task currTask = this.taskList.get(numToDelete - 1);
        this.taskList.remove(numToDelete - 1);
    }
}
