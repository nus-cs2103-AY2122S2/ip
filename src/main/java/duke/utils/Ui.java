package duke.utils;

import duke.task.Task;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * Show error message.
     *
     * @param errorMessage the error message
     * @return error message
     */
    public String showErrorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * No task left Ui.
     *
     * @return no task left prompt
     */
    public String noTaskLeft() {
        return "You are done for the day, or are you?";
    }

    /**
     * Show added task and Ui.
     *
     * @param tasks the TaskList tasks
     * @param task  the task
     * @return added task prompt
     */
    public String addedTask(TaskList tasks, Task task) {
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "task" : "tasks";
        return "Got it. I've added this task: \n" + " " + task
                + "\nNow you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
    }

    /**
     * Show deleted task and Ui.
     *
     * @param numOfTasksDeleted       the number of task deleted
     * @return deleted task prompt
     */
    public String deletedTask(int numOfTasksDeleted) {
        String taskOrTasks = numOfTasksDeleted <= 1 ? "task" : "tasks";
        String isOrAre = numOfTasksDeleted <= 1 ? "is" : "are";

        return String.format("Noted, the following %s %s deleted:\n\n", taskOrTasks, isOrAre);
    }

    /**
     * Show deleted all task Ui.
     *
     * @return deleted all tasks prompt
     */
    public String clearAll() {
        return "All tasks have been removed!";
    }

    /**
     * Show exited Ui.
     *
     * @return exited prompt.
     */
    public String exited() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Show listed task Ui.
     *
     * @param index the index of the task to list
     * @param task  the task
     * @return listed all task prompt
     */
    public String listed(int index, Task task) {
        return index + "." + task;
    }

    /**
     * Show marked task Ui.
     *
     * @param numOfTasksDeleted the number of tasks marked
     * @return marked task prompt
     */
    public String marked(int numOfTasksDeleted) {
        String taskOrTasks = numOfTasksDeleted <= 1 ? "task" : "tasks";
        return String.format("Nice! I've marked the following %s as done:\n\n", taskOrTasks);
    }

    /**
     * Show unmarked task Ui.
     *
     * @param numOfTasksDeleted the number of tasks unmarked
     * @return unmarked task prompt
     */
    public String unmarked(int numOfTasksDeleted) {
        String taskOrTasks = numOfTasksDeleted <= 1 ? "task" : "tasks";
        return String.format("OK, I've marked the following %s as not done yet:\n\n", taskOrTasks);
    }

    /**
     * Show founded task on the same date/time and Ui.
     *
     * @param numberOfTasksOnSameDate the number of tasks on the same date
     * @param time                    the date/time
     * @return founded task on the same date/time prompt
     */
    public String foundTaskOnSameDate(int numberOfTasksOnSameDate, String time) {
        String taskOrTasks = numberOfTasksOnSameDate <= 1 ? "task" : "tasks";
        return String.format("Found %d %s with date/time %s:\n\n", numberOfTasksOnSameDate, taskOrTasks, time);
    }

    /**
     * Show founded task with the same keyword.
     *
     * @param numberOfTasksMatchKeyword the number of tasks on the same date
     * @param keyword                   the keyword to search for
     * @return founded task with the given keyword prompt
     */
    public String foundTasksMatchKeyword(int numberOfTasksMatchKeyword, String keyword) {
        String taskOrTasks = numberOfTasksMatchKeyword <= 1 ? "task" : "tasks";
        return String.format("Found %d %s containing keyword \"%s\":\n\n",
                numberOfTasksMatchKeyword, taskOrTasks, keyword);
    }

    /**
     * View schedules on date string.
     *
     * @param numberOfTasksMatchKeyword the number of tasks match keyword
     * @param time                      the time
     * @return schedules on the given date
     */
    public String viewSchedulesOnDate(int numberOfTasksMatchKeyword, String time) {
        String taskOrTasks = numberOfTasksMatchKeyword <= 1 ? "task" : "tasks";
        return String.format("You have %d %s on %s:\n"
                        + "(Tasks on this date without time will be placed at the end of the list)\n\n",
                numberOfTasksMatchKeyword, taskOrTasks, time);
    }
}
