package tasks;

import tasks.Task;

import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> dukeList = new ArrayList<>();

    public static void add(Task task) {
        dukeList.add(task);
    }

    public static void delete(int taskIndex) {
        dukeList.remove(taskIndex);
    }

    public static Task getTask(int taskIndex) {
        return dukeList.get(taskIndex);
    }
}
