package commands;

import myTasks.Task;

import java.util.List;

/**
 * The DeleteTask class removes a task that is currently in the task list.
 */
public class DeleteTask {
    /**
     * The method delete removes a task that is currently in the task list.
     * @param number the task number that is going to be deleted.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void delete(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;
        System.out.println(list.get(taskNum).toString());
        list.remove(taskNum);
    }
}
