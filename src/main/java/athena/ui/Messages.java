package athena.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import athena.tasks.TaskList;

/**
 * Provides helper methods and constants for generating Athena's dialog output.
 */
public class Messages {

    public static final String WELCOME_MESSAGE = "Greetings! My name is Athena. What can I help you with?";
    public static final String PRE_DELETION_MESSAGE = "Alright, I will delete the following task from the list:";
    public static final String TASK_ADDING_MESSAGE = "Okay, I've added this task to your list.";
    public static final String TASK_LISTING_MESSAGE = "Here's the current list of tasks:";
    public static final String MARKED_TASK_AS_DONE = "Alright, I've marked the following task as done:";
    public static final String MARKED_TASK_AS_NOT_DONE = "Alright, I've marked the following task as not done:";
    public static final String ONE_TASK_IN_LIST_MESSAGE = "Now you have 1 task in your list.";
    public static final String SAVE_ERROR_MESSAGE = "I encountered a problem saving to disk: ";
    public static final String NO_REMINDERS_MESSAGE =
            "Congratulations! You have nothing coming up in the given time period";
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    public static String getMultiLineString(List<String> inputs) {
        return String.join("\n", inputs);
    }

    public static String getMultiLineString(String... inputs) {
        return String.join("\n", inputs);
    }

    public static String getSpecificTasksFoundDialog(TaskList taskList, List<Integer> taskNumbers,
            String searchPhrase) {
        assert taskNumbers.size() > 0;
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(String.format("Here are the tasks containing the phrase '%s'", searchPhrase));
        for (int taskNumber : taskNumbers) {
            outputs.add(String.format("%d. %s", taskNumber, taskList.getTaskString(taskNumber)));
        }
        return getMultiLineString(outputs);
    }

    public static String getRemindersFoundInRangeDialog(TaskList taskList, List<Integer> taskNumbers,
            LocalDate startDate, LocalDate endDate) {
        assert taskNumbers.size() > 0;
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(String.format("You have the following tasks coming up between %s to %s",
                startDate.format(OUTPUT_FORMATTER), endDate.format(OUTPUT_FORMATTER)));

        for (int taskNumber : taskNumbers) {
            outputs.add(String.format("%d. %s", taskNumber, taskList.getTaskString(taskNumber)));
        }
        return getMultiLineString(outputs);
    }

    public static String getTaskAddingDialog(TaskList taskList, int taskNumber) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(TASK_ADDING_MESSAGE);
        outputs.add(taskList.getTaskString(taskNumber));
        outputs.add(getCurrentNumberOfTasksDialog(taskList));
        return getMultiLineString(outputs);
    }

    public static String getTaskListDialog(TaskList taskList) {
        ArrayList<String> outputs = new ArrayList<>();
        outputs.add(TASK_LISTING_MESSAGE);
        outputs.add(taskList.toString());
        return getMultiLineString(outputs);
    }

    public static String getMarkTaskDialog(TaskList taskList, int taskNumber) {
        String taskToString = taskList.getTaskString(taskNumber);
        return Messages.getMultiLineString(MARKED_TASK_AS_DONE, taskToString);
    }

    public static String getUnmarkTaskDialog(TaskList taskList, int taskNumber) {
        String taskToString = taskList.getTaskString(taskNumber);
        return Messages.getMultiLineString(MARKED_TASK_AS_NOT_DONE, taskToString);
    }

    public static String getPreDeletionDialog(TaskList taskList, int taskNumber) {
        String taskToBeDeleted = taskList.getTaskString(taskNumber);
        return getMultiLineString(PRE_DELETION_MESSAGE, taskToBeDeleted);
    }

    public static String getNoMatchesFoundDialog(String searchPhrase) {
        return String.format("Sorry, no tasks were found containing the phrase '%s'", searchPhrase);
    }

    public static String getCurrentNumberOfTasksDialog(TaskList taskList) {
        if (taskList.getNumberOfTasks() == 1) {
            return ONE_TASK_IN_LIST_MESSAGE;
        } else {
            return String.format("Now you have %d tasks in your list.", taskList.getNumberOfTasks());
        }
    }
}
