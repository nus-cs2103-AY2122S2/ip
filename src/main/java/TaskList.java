import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = new LinkedList<>();
        for (int i = 0; i < tasks.size(); i++) {
            this.tasks.add(tasks.get(i));
        }
    }

    TaskList() {
        this.tasks = new LinkedList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isEmpty()) {
                int index = i + 1;
                s += "  " + index + ". " + tasks.get(i) + "\n";
            }
            else {
                s += "You now have " + i +" tasks on your list.";
                break;
            }
        }
        if (tasks.isEmpty()) {
            s =  "You now have 0 tasks on your list.";
        }

        return s;
    }
}
