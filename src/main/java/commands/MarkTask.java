package commands;

import java.util.List;

import mytasks.Task;

/**
 * The MarkTask class contains methods to indicate if the task is complete.
 */
public class MarkTask {
    /**
     * Indicates that the task has been completed
     * @param number the task number that has been completed.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static String mark(String number, List<Task> list) {
        StringBuilder sb = new StringBuilder();
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsDone();
        sb.append(list.get(taskNum).toString() + "\n");
        sb.append("Nice! I've marked this task as done:\n");
        return sb.toString();
    }

    /**
     * Indicates that the task has not been completed
     * @param number the task number that has not been completed.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static String unmark(String number, List<Task> list) {
        StringBuilder sb = new StringBuilder();
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsNotDone();
        sb.append(list.get(taskNum).toString() + "\n");
        sb.append("Nice! I've marked this task as not done:\n");
        return sb.toString();
    }
}
