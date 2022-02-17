package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the text user interface of Duke, which deals
 * with providing the messages sent from Duke to the user.
 */
public class Ui {

    /**
     * Gives the welcome message.
     *
     * @return a message from duke welcoming the user.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\nHello! I'm Duke :)";
    }

    /**
     * Gives an error message when saved data cannot be retrieved.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating a failure to load data.
     */
    public String showLoadingError(String errorMessage) {
        return "There was an error in retrieving saved data:\n" + errorMessage
                + "\nI couldn't find any data to load, so I've created an empty task list.";

    }

    /**
     * Gives a message indicating data has been successfully loaded.
     *
     * @return a String containing the latest retrieved task data.
     */
    public String showLoadingSuccess() {
        return "I've retrieved your latest task list data. Type 'list' to see the tasks!";
    }

    /**
     * Gives an error message when data cannot be saved into a txt file.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating a failure to save data.
     */
    public String showSavingError(String errorMessage) {
        return "There was an error in saving the data:\n" + errorMessage;
    }

    /**
     * Gives an error message when the user entered an invalid command.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating an invalid command was received.
     */
    public String showCommandError(String errorMessage) {
        return "Oops, there might be an error in the command entered:\n\"" + errorMessage + "\"";
    }

    /**
     * Gives the contents of the task list.
     *
     * @param taskList the task list to be shown.
     * @return a String containing the contents of the task list.
     */
    public String showTaskList(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Gives a message indicating a task was successfully added.
     *
     * @param task the task that was added.
     * @param taskList the task list to which the task was added.
     * @return a message from duke to show a task was added.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        assert taskList.getLength() >= 0 : "taskList.getLength() should return an int not less than 0";
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Gives a message to show a task was marked as complete.
     *
     * @param task the task that was marked as complete.
     * @return a message from duke to show a task was marked.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Gives a message to show a task was marked as incomplete.
     *
     * @param task the task that was marked as incomplete.
     * @return a message from duke to show a task was unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet\n" + task;
    }

    /**
     * Gives a message to show a task was deleted.
     *
     * @param task the task that was deleted.
     * @param taskList the task list from which the task was deleted.
     * @return a message from duke to show a task was deleted.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        assert taskList.getLength() >= 0 : "taskList.getLength() should return an int not less than 0";

        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Gives the results of searching a task list.
     *
     * @param findResults the tasks found, in the form of a task list.
     * @return the search results of the FindCommand execution.
     */
    public String showFindResult(TaskList findResults) {
        if (findResults.getLength() == 0) {
            return "I couldn't find any task matching that keyword.";
        }

        return "Here are the matching tasks in your task list:\n" + findResults;
    }

    /**
     * Gives a message showing the tasks which are scheduled within a specified
     * number of days from today.
     *
     * @param upcomingTasks the tasks that are scheduled within a specified number of days from today.
     * @param daysFromToday the number of days from today.
     * @return the upcoming tasks, with an accompanying message from Duke.
     */
    public String showRemindResult(TaskList upcomingTasks, int daysFromToday) {
        boolean isReminderForToday = daysFromToday == 0;
        boolean hasEmptySchedule = upcomingTasks.getLength() == 0;

        if (isReminderForToday && hasEmptySchedule) {
            return "You're free! You have no tasks scheduled for today!";
        } else if (!isReminderForToday && hasEmptySchedule) {
            return "You're free! You have no tasks scheduled for the next " + daysFromToday + " days!";
        } else if (isReminderForToday && !hasEmptySchedule) {
            return "Better get to work! You have the following task(s) scheduled for today:\n" + upcomingTasks;
        } else {
            return "For the next " + daysFromToday + " day(s), you have the following task(s) scheduled:\n"
                    + upcomingTasks;
        }
    }

    /**
     * Gives a goodbye message.
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again!";
    }

}
