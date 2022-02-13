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
        for (Task t : taskList.getTaskList()) {
            String currentDescription = t.getDescription().toLowerCase();
            if (descriptionToBeChecked.equals(currentDescription)) {
                return t;
            }
        }
        return task;
    }
}
