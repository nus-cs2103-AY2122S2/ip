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
    public static String delete(String number, List<Task> list, int taskCount) {
        StringBuilder sb = new StringBuilder();
        int taskNum = Integer.parseInt(number) - 1;
        sb.append("Noted. I've removed this task:\n");
        sb.append(list.get(taskNum).toString() + "\n");
        assert !(taskNum < 0);
        list.remove(taskNum);
        return sb.toString();
    }
}
