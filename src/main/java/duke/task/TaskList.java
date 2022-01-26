package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<duke.task.Task> toDoList;

    public TaskList(ArrayList<duke.task.Task> arr) {
        this.toDoList = arr;
    }

    public ArrayList<duke.task.Task> getToDoList() {
        return toDoList;
    }

    public void add(duke.task.Task task) {
        this.toDoList.add(task);
    }

    public duke.task.Task get(int idx) {
        return this.toDoList.get(idx);
    }

    public duke.task.Task remove(int idx) {
        return this.toDoList.remove(idx);
    }
}
