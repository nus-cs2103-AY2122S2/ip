package saitama.ui;

import java.util.List;

import saitama.tasks.Task;
import saitama.tasks.TaskList;

public class Ui {
    /**
     * Returns the error message.
     *
     * @param errorMessage The error message to be printed.
     * @return The error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns the save message.
     *
     * @return The save message.
     */
    public String showExit() {
        String reply = String.format("OK...\nYour tasks have been saved!\nGoodbye!");
        return reply;
    }

    /**
     * Returns the add task message.
     *
     * @param task The Task to add.
     * @param taskList The TaskList that tracks the current tasks.
     * @return  The add task message.
     */
    public String showAddTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has been added to the list:\n"
                + "%s\nNow you have %d tasks in the list", task, taskList.numOfTasks());
        return reply;
    }

    /**
     * Returns the delete task message.
     *
     * @param task The Task to delete.
     * @param taskList The TaskList that tracks the current tasks.
     * @return  The delete task message.
     */
    public String showDeleteTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has been removed from the list:\n"
                + "%s\nNow you have %d tasks in the list", task, taskList.numOfTasks());
        return reply;
    }

    /**
     * Returns the mark task message.
     *
     * @param task The Task to mark.
     * @param taskList The TaskList that tracks the current tasks.
     * @return  The mark task message.
     */
    public String showMarkTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has marked:\n%s",
                task, taskList.numOfTasks());
        return reply;
    }

    /**
     * Returns the unmark task message.
     *
     * @param task The Task to unmark.
     * @param taskList The TaskList that tracks the current tasks.
     * @return  The unmark task message.
     */
    public String showUnmarkTask(Task task, TaskList taskList) {
        String reply = String.format("OK...\nThe following task has unmarked:\n%s",
                task, taskList.numOfTasks());
        return reply;
    }

    /**
     * Returns the matching tasks message when FindCommand is run.
     *
     * @param matchingTasks The List of matching tasks.
     * @return The matching tasks message.
     */
    public String showMatchingTasks(List<Task> matchingTasks) {
        String reply = "OK...\n";
        int counter = 1;
        if (matchingTasks.size() == 0) {
            return "There are no matching tasks in your list!";
        } else {
            reply += "Here are the matching tasks in your list:";
            for (Task task : matchingTasks) {
                reply += "\n" + counter + "." + task;
                counter += 1;
            }
        }
        return reply;
    }

    /**
     * Returns the list message.
     *
     * @param taskList The TaskList.
     * @return The list message.
     */
    public String showList(TaskList taskList) {
        String reply = "OK...\n";
        if (taskList.numOfTasks() == 0) {
            return "There are no tasks in your list!";
        } else {
            reply += "Here are the tasks in your list:\n";
            reply += taskList;
        }
        return reply;
    }
}

