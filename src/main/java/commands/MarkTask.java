package commands;

import myTasks.Task;

import java.util.List;

/**
 * The MarkTask class contains methods to indicate if the task is complete.
 */
public class MarkTask {
    /**
     * The mark indicates that the task has been completed
     * @param number the task number that has been completed.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void mark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsDone();
        System.out.println(list.get(taskNum).toString());
    }

    /**
     * The unmark indicates that the task has not been completed
     * @param number the task number that has not been completed.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void unmark(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsNotDone();
        System.out.println(list.get(taskNum).toString());
    }
}
