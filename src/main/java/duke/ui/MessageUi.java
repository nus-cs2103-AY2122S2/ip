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
     * @return Welcome message.
     */
    public String showWelcomeMessage() {
        return "Ello, my name is Ekud, your personal task tracking bot.";
    }

    /**
     * Outputs a message when the text file cannot be found in the
     * specified directory.
     * @return Cannot find file message.
     */
    public String showFileNotFoundMessage() {
        return "Oh no, looks like I have some trouble finding your task list.\n"
                + "Fred not, Ekud has created the file for you!";
    }

    /**
     * Outputs a message when the text file can be found in the
     * specified directory.
     * @return Can find file message.
     */
    public String showFileFoundMessage(TaskList tasks) {
        return "Task list successfully loaded\n"
                + "You currently have " + tasks.getTaskSize() + " task(s) in your list.";
    }

    /**
     * Outputs a message when user exits the programme.
     * @return Exit message.
     */
    public String showExitMessage() {
        return "Goodbye, feel free to use Ekud anytime!";
    }

    /**
     * Outputs a message when the user adds a task.
     * @return Task added message.
     */
    public String showAddTaskMessage(TaskList taskList, Task task) {
        return "Got it. I've added this task: \n"
                + task.toString() + "\nNow you have "
                + taskList.getTaskSize() + " tasks in the list.";
    }

    /**
     * Outputs a message when the user marks a task.
     * @return Task mark message.
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Outputs a message when the user unmarks a task.
     * @return Task unmark message.
     */
    public String showUnMarkMessage(Task task) {
        return "OK, I've marked this task as not done yet: \n" + task.toString();
    }

    /**
     * Outputs a message when the user delete a task.
     * @return Task delete message.
     */
    public String showDeleteMessage(Task task, int taskSize) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\nNow you have "
                + taskSize + " tasks in the list.";
    }

    /**
     * Outputs a message when user views an empty task list.
     * @return Empty task list message.
     */
    public String showEmptyListMessage() {
        return "You do not have any tasks in your list!";
    }

    /**
     * Outputs a message when user views a task list.
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
     * @return Clear task list confirmation message.
     */
    public String showClearListConfirmationMessage() {
        return "Are you sure you want Ekud to clear your task list?";
    }

    /**
     * Outputs a message when user confirms to clear task list.
     * @return Task list cleared message.
     */
    public String showClearListMessage() {
            return "Got it, Ekud has cleared your task list";
    }
}

