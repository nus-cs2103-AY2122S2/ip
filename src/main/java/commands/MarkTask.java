package commands;

import java.util.List;

import duke.Ui;
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
    public static void mark(String number, List<Task> list, int taskCount) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsDone();
        System.out.println(list.get(taskNum).toString());
        Ui.printAndSave(taskCount);
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Indicates that the task has not been completed
     * @param number the task number that has not been completed.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static void unmark(String number, List<Task> list, int taskCount) {
        int taskNum = Integer.parseInt(number) - 1;
        list.get(taskNum).markAsNotDone();
        System.out.println(list.get(taskNum).toString());
        Ui.printAndSave(taskCount);
        System.out.println("OK, I've marked this task as not done yet:");
    }
}
