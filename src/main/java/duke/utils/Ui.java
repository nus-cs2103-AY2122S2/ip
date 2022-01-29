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
     */
    public String showErrorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * No task left Ui.
     */
    public String noTaskLeft() {
        return "You are done for the day, or are you?";
    }

    /**
     * Show added task and Ui.
     *
     * @param tasks the TaskList tasks
     * @param task  the task
     */
    public String addedTask(TaskList tasks, Task task) {
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "task" : "tasks";
        return "Got it. I've added this task: \n" + " " + task
                + "\nNow you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
    }

    /**
     * Show deleted task and Ui.
     *
     * @param tasks       the TaskList tasks
     * @param taskDeleted the task deleted
     */
    public String deletedTask(TaskList tasks, Task taskDeleted) {
        String taskOrTasks = tasks.getTaskList().size() <= 1 ? "duke/task" : "tasks";
        return "Noted. I've removed this task: \n" + " " + taskDeleted + "\n"
                + "Now you have " + tasks.getTaskList().size() + " " + taskOrTasks + " in the list.";
    }

    /**
     * Show deleted all task Ui.
     */
    public String deletedAll() {
        return "All tasks have been removed!";
    }

    /**
     * Show exited Ui.
     */
    public String exited() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Show listed task Ui.
     *
     * @param index the index of the task to list
     * @param task  the task
     */
    public String listed(int index, Task task) {
        return index + "." + task;
    }

    /**
     * Show marked task Ui.
     *
     * @param task the task to be marked
     */
    public String marked(Task task) {
        return "Nice! I've marked this task as done: \n " + task;
    }

    /**
     * Show unmarked task Ui.
     *
     * @param task the task to be unmarked
     */
    public String unmarked(Task task) {
        return "OK, I've marked this task as not done yet: \n " + task;
    }

    /**
     * Show founded task on the same date and Ui.
     *
     * @param numberOfTasksOnSameDate the number of tasks on the same date
     * @param time                    the date/time
     */
    public String foundTaskOnSameDate(int numberOfTasksOnSameDate, String time) {
        String taskOrTasks = numberOfTasksOnSameDate <= 1 ? "duke/task" : "tasks";
        return String.format("Found %d %s with date/time %s.\n", numberOfTasksOnSameDate, taskOrTasks, time);
    }

    /**
     * Show founded task with the same keyword.
     *
     * @param numberOfTasksMatchKeyword the number of tasks on the same date
     * @param keyword                   the keyword to search for
     */
    public String foundTasksMatchKeyword(int numberOfTasksMatchKeyword, String keyword) {
        String taskOrTasks = numberOfTasksMatchKeyword <= 1 ? "task" : "tasks";
        return String.format("Found %d %s containing keyword \"%s\".\n",
                numberOfTasksMatchKeyword, taskOrTasks, keyword);
    }
}
