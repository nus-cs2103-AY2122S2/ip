package ui;

import task.Task;
import task.TaskList;

/**
 * Handles all generation of appropriate responses for specific commands and returns it as a String to be displayed
 * in the JavaFX Ui window
 */
public class Ui {

    /** Returns goodbye message shown to user on program termination */
    public String goodByeResponse() {
        return String.format("If that'll be all sir, i shall retire for the day.\n"
                            + "I'll be in my quarters if you require me");
    }

    /** Returns the list of all the tasks that are currently being tracked by Cleese*/
    public String listResponse(TaskList taskList) {
        assert taskList != null : "taskList was not passed into function";
        if (taskList.size() == 0) {
            return "Sir I'm afraid you have no tasks right now";
        } else {
            return String.format(taskList.toString());
        }
    }

    /** Returns acknowledgment message as well as task that user has marked as done */
    public String markedMessageResponse(Task task) {
        assert task != null : "task was not passed into function";
        return String.format("Ok, I've marked this task as done:\n%s", task.toString());
    }

    /** Returns acknowledgment message as well as task that user has marked as undone */
    public String unmarkedMessageResponse(Task task) {
        assert task != null : "task was not passed into function";
        return String.format("Ok, I've marked this task as not done yet:\n%s", task.toString());
    }

    /** Returns acknowledgment message as well as task that user has added to the TasksList */
    public String addedAckResponse(Task task, TaskList taskList) {
        assert task != null : "task was not passed into function";
        assert taskList != null : "taskList was not passed into function";
        return String.format("Got it. I've added this task:\n%s\n"
                                + "Now you have %d tasks in the list", task.toString(), taskList.size());
    }

    /** Returns acknowledgment message as well as task that user has removed from the TasksList */
    public String removedAckResponse(Task task, TaskList taskList) {
        assert task != null : "task was not passed into function";
        assert taskList != null : "taskList was not passed into function";
        return String.format("Noted. I've removed this task:\n%s\n"
                                + "Now you have %d tasks in the list", task.toString(), taskList.size());
    }

    /** Returns the acknowledgment message that is shown to the user before the results of find() */
    public String findMessageResponse(String foundTasks) {
        assert foundTasks != null : "foundTasks was not passed into function";
        return String.format("Here are the matching tasks in your list:\n%s", foundTasks);
    }

    /** Returns the help message that is shown to the user to help in using Cleese */
    public String helpResponse() {
        return String.format("Not to worry sir, the commands are\n"
                                + "Make Tasks:      todo | event | deadline\n"
                                + "Remove Tasks: delete\n"
                                + "Mark Tasks:      mark | unmark\n"
                                + "View Tasks:      list | find\n"
                                + "Exit:                 bye");
    }
}
