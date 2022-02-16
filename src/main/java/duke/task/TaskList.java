package duke.task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public String toString() {
        return lst.toString();
    }
    public void add(Task task) {
        lst.add(task);
    }

    public void delete(int index) {
        lst.remove(index);
    }

    public Task get(int index) {
        return lst.get(index);
    }

    public int size() {
        return lst.size();
    }
}
