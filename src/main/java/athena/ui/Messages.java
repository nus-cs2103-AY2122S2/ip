// Stretch goal: abstract out the messages
package athena.ui;

import java.util.ArrayList;
import java.util.List;

import athena.tasks.TaskList;

/**
 * Provides helper methods for generating Athena's dialogue output.
 */
public class Messages {
    public static String getMultiLineString(List<String> inputs) {
        return String.join("\n", inputs);
    }

    public static String getSpecificTasksDialog(TaskList taskList, List<Integer> taskNumbers) {
        assert taskNumbers.size() > 0;
        ArrayList<String> outputs = new ArrayList<>();
        for (int taskNumber : taskNumbers) {
            outputs.add(String.format("%d. %s", taskNumber, taskList.getTaskString(taskNumber)));
        }
        return getMultiLineString(outputs);
    }

    public static String getTaskAddingDialog(TaskList taskList, int taskNumber) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add("Okay, I've added this task to your list.");
        outputs.add(taskList.getTaskString(taskNumber));
        outputs.add(getCurrentNumberOfTasksDialog(taskList));
        return getMultiLineString(outputs);
    }

    public static String getTaskListDialog(TaskList taskList) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add("Here's the current list of tasks:");
        outputs.add(taskList.toString());
        return getMultiLineString(outputs);
    }

    public static String getCurrentNumberOfTasksDialog(TaskList taskList) {
        if (taskList.getNumberOfTasks() == 1) {
            return "Now you have 1 task in your list.";
        } else {
            return String.format("Now you have %d tasks in your list.", taskList.getNumberOfTasks());
        }
    }
}
