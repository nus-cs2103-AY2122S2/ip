package Tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arrayList;

    public TaskList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
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
