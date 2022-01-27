import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    protected ArrayList<Task> getTasks() {
        return taskList;
    }

}
