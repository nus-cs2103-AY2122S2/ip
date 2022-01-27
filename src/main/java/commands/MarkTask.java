package commands;

import myTasks.Task;

import java.util.List;

public class MarkTask {
    public static void mark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsDone();
        System.out.println(list.get(taskNum).toString());
    }

    public static void unmark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsNotDone();
        System.out.println(list.get(taskNum).toString());
    }
}
