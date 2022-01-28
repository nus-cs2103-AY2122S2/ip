package Tasks;

import java.util.ArrayList;

/**
 * TaskList is a class that deals with the management and storage of tasks
 */
public class TaskList {
    private ArrayList<Task> arrayList;

    /**
     * Constructor for the TaskList class
     * @param arrayList An array list containing the tasks to be stored
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }

    public TaskList() {
        this.arrayList = new ArrayList<>();
    }

    public void add(Task task) {
        arrayList.add(task);
    }

    public Task delete(int index) {
        return arrayList.remove(index);
    }

    public Task get(int index) {
        return arrayList.get(index);
    }

    public int getSize() {
        return arrayList.size();
    }


}
