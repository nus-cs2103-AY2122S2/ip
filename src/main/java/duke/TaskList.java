package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> toDoList;

    public TaskList(Storage storage) {
        toDoList = storage.load();
    }

    public static ArrayList<Task> getToDoList() {
        return toDoList;
    }

    public static void addTask(Task task) {
        toDoList.add(task);
    }

    public static void removeTask(int index) {
        toDoList.remove(index);
    }
}
