package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    // Constructor used for creating stubs for testing
    public TaskList(ArrayList<Task> manualList) {
        this.toDoList = manualList;
    }

    public TaskList(Storage storage) {
        toDoList = storage.load();
    }

    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    public void addTask(Task task) {
        toDoList.add(task);
    }

    public void removeTask(int index) {
        toDoList.remove(index);
    }
}
