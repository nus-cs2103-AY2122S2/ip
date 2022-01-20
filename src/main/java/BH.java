import java.util.*;
public class BH {
    private ArrayList<Task> list;

    BH() {
        this.list = new ArrayList<Task>();
    }

    void addToList(Task task) {
        this.list.add(task);
    }

    Task deleteTask(int index) {
        Task task =  this.list.get(index);
        this.list.remove(index);
        return task;
    }



    String getList() {
        String s = "";
        for (int i = 0; i < this.list.size(); i++) {
            s = s + (i + 1) + ". " + list.get(i) + "\n";
        }
        return s;
    }

    int getListSize() {
        return this.list.size();
    }

    String mark(int index) {
        this.list.get(index).mark();
        return this.list.get(index).toString();
    }

    String unmark(int index) {
        this.list.get(index).unmark();
        return this.list.get(index).toString();
    }
}
