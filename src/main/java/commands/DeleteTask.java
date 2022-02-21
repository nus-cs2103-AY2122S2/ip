package commands;

import java.util.List;

import mytasks.Task;

/**
 * The DeleteTask class removes a task that is currently in the task list.
 */
public class DeleteTask {
    /**
     * Removes a task that is currently in the task list.
     * @param number the task number that is going to be deleted.
     * @param list contains the list of tasks that are currently being tracked.
     */
    public static String delete(String number, List<Task> list) {
        int taskNum = Integer.parseInt(number) - 1;

        assert !(taskNum < 0);
        list.remove(taskNum);

        return printDeleteTask(list, taskNum);
    }

    /**
     * Tells the user that the task has been deleted.
     * @param list contains the list of tasks that are currently being tracked.
     * @param taskNum TaskNum of task that is being deleted.
     */
    private static String printDeleteTask(List<Task> list, int taskNum) {
        StringBuilder sb = new StringBuilder();

        sb.append("Noted. I've removed this task:\n");
        sb.append(list.get(taskNum).toString() + "\n");

        return sb.toString();
    }
}
