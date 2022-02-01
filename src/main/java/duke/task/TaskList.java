package duke.task;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    protected List<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new LinkedList<>();
        this.tasks.addAll(tasks);
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

    public TaskList find(String word) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(word)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        return new TaskList(matchingTasks);
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
