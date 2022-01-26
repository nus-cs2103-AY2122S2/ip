import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList(ArrayList<Task> arr) {
        this.toDoList = arr;
    }

    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    public void add(Task task) {
        this.toDoList.add(task);
    }

    public Task get(int idx) {
        return this.toDoList.get(idx);
    }

    public Task remove(int idx) {
        return this.toDoList.remove(idx);
    }
}
