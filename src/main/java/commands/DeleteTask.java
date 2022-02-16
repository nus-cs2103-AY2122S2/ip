package commands;

import myTasks.Task;

import java.util.List;

import duke.Ui;

/**
 * The DeleteTask class removes a task that is currently in the task list.
 */
public class DeleteTask {
    /**
     * Removes a task that is currently in the task list.
     * @param number the task number that is going to be deleted.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void delete(String number, List<Task> list, int taskCount) {
        int taskNum = Integer.parseInt(number) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskNum).toString());
        list.remove(taskNum);
        Ui.printAndSave(taskCount - 1);
    }
}
