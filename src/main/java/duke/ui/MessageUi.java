package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that deals output messages to the user
 * depending on which command the user call.
 */
public class MessageUi {

    /**
     * Outputs a welcome message to the user when the programme launch.
     *
     * @return Welcome message.
     */
    public String showWelcomeMessage() {
        return "I'm BING CHILLING bot";
    }

    /**
     * Outputs a message when the text file cannot be found in the
     * specified directory.
     *
     * @return Cannot find file message.
     */
    public String showFileNotFoundMessage() {
        return "Bing Chilling could not locate your task list, \n"
                + "and has created one for you.";
    }

    /**
     * Outputs a message when the text file can be found in the
     * specified directory.
     *
     * @return Can find file message.
     */
    public String showFileFoundMessage(TaskList tasks) {
        return "Bing Chilling has loaded your task.\n"
                + "You currently have " + tasks.getTaskSize() + " task(s) in your list.";
    }

    /**
     * Outputs a message when user exits the programme.
     *
     * @return Exit message.
     */
    public String showExitMessage() {
        return "Bing Chilling bids farewell!";
    }

    /**
     * Outputs a message when the user adds a task.
     *
     * @return Task added message.
     */
    public String showAddTaskMessage(TaskList taskList, Task task) {
        return "Bing Chilling has added this task: \n"
                + task.toString() + "\nNow you have "
                + taskList.getTaskSize() + " tasks in the list.";
    }

    /**
     * Outputs a message when the user marks a task.
     *
     * @return Task mark message.
     */
    public String showMarkMessage(Task task) {
        return "Okie, Bing Chilling has marked this task as done: \n" + task.toString();
    }

    /**
     * Outputs a message when the user unmarks a task.
     *
     * @return Task unmark message.
     */
    public String showUnMarkMessage(Task task) {
        return "Okie, Bing Chilling has marked this task as not done: \n" + task.toString();
    }

    public String showPostponeMessage(Task task) {
        return "Aite! Bing Chilling has postponed this task: \n" + task.toString();
    }

    /**
     * Outputs a message when the user delete a task.
     *
     * @return Task delete message.
     */
    public String showDeleteMessage(Task task, int taskSize) {
        return "Orite, Bing Chilling has removed this task:\n"
                + task.toString() + "\nNow you have "
                + taskSize + " tasks in the list.";
    }

    /**
     * Outputs a message when user views an empty task list.
     *
     * @return Empty task list message.
     */
    public String showEmptyListMessage() {
        return "You have not yet added any task!";
    }

    /**
     * Outputs a message when user views a task list.
     *
     * @return All task in task list message.
     */
    public String showListMessage(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.getTaskSize(); i++) {
            stringBuilder.append(i + 1 + "." + tasks.getTask(i + 1).toString() + "\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Outputs a message when user wants to clear task list.
     *
     * @return Clear task list confirmation message.
     */
    public String showClearListConfirmationMessage() {
        return "Do you want Bing Chilling to clear your task list?";
    }

    /**
     * Outputs a message when user confirms to clear task list.
     *
     * @return Task list cleared message.
     */
    public String showClearListMessage() {
        return "Got it, Bing Chilling has cleared your task list";
    }

    /**
     * Outputs a message when an invalid command is supplied.
     *
     * @return Invalid command message.
     */
    public String showInvalidCommandMessage() {
        return "Bing Chilling does not recognise the command";
    }

    /**
     * Outputs a message when a command that is of the incorrect format is supplied.
     *
     * @return Incorrect command format message.
     */
    public String showInvalidFormatMessage() {
        return "Bing Chilling does not recognise the command format";
    }
}

