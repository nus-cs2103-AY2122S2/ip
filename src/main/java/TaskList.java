import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public int size() {
        return this.listOfTasks.size();
    }
    public Task get(int index) {
        return this.listOfTasks.get(index);
    }

    public Task remove(int index) {
        return this.listOfTasks.remove(index);
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    public ArrayList<Task> getList() {return this.listOfTasks;}
}
