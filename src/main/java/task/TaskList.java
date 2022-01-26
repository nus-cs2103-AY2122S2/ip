package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void add(Task t) {
        list.add(t);
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }


}