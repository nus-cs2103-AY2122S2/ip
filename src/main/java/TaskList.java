import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList add(Task t) {
        ArrayList<Task> newList = list;
        newList.add(t);
        return new TaskList(newList);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public TaskList set(int index, Task t) {
        ArrayList<Task> newList = list;
        newList.set(index, t);
        return new TaskList(newList);
    }

    public TaskList remove(int index) {
        ArrayList<Task> newList = list;
        newList.remove(index);
        return new TaskList(newList);
    }

    public int size() {
        return list.size();
    }

}
