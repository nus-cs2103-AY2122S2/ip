package duke.extension;

import duke.task.Task;
import duke.main.TaskList;

import java.util.Locale;

/**
 * an extension to check if the task added is duplicated with the existing tasks
 */
public class CheckDuplicate {
    public static Task checkDuplicate(Task task,TaskList taskList) {
        String descriptionToBeChecked = task.getDescription().toLowerCase();
        Task t = checkIfTaskContainsInTaskList(taskList, descriptionToBeChecked);
        if (t != null) return t;
        return task;
    }

    /**
     * Check if there is duplicated task in tasklists, duplicated
     * means the description and the type of the task are the same
     * @param taskList current list of tasks.
     * @param descriptionToBeChecked the description of the task
     * @return
     */
    private static Task checkIfTaskContainsInTaskList(TaskList taskList, String descriptionToBeChecked) {
        for (Task t : taskList.getTaskList()) {
            String currentDescription = t.getDescription().toLowerCase();
            if (descriptionToBeChecked.equals(currentDescription)) {
                return t;
            }
        }
        return null;
    }
}
