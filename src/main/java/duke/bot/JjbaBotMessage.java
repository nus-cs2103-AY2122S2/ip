package duke.bot;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a collection of messages to respond to user input commands.
 * A <code>JJBABotMessage</code> object can be created to respond to the user
 * in the JJBA style.
 */
public class JjbaBotMessage extends BotMessage {
    /**
     * Returns the message to be printed when a command of type 'list' is executed.
     *
     * @param isEmpty whether list is empty.
     * @return list message.
     */
    @Override
    public String getListMessage(boolean isEmpty) {
        return isEmpty ? "There are no task available." : "Here are the task in your list:";
    }

    /**
     * Returns the message to be printed when an invalid command is entered.
     *
     * @return list message.
     */
    @Override
    public String getInvalidCommandMessage() {
        return "Invalid Command!";
    }

    /**
     * Returns the message to be printed when a command of type 'add' is executed.
     *
     * @param taskList a task list containing all task.
     * @param task the task that is added.
     * @return add message.
     */
    @Override
    public String getAddMessage(TaskList taskList, Task task) {
        return "Task added: \n   " + task + "\n" + getTaskLeft(taskList);
    }

    /**
     * Returns the message to be printed when a command of type 'delete' is executed.
     *
     * @param taskList a task list containing all task.
     * @param task the task that is deleted.
     * @return delete message.
     */
    @Override
    public String getDeleteMessage(TaskList taskList, Task task) {
        return "Task removed!\n   " + task;
    }

    /**
     * Returns the message to be printed when a command of type 'mark' is executed.
     *
     * @param task the task that is marked.
     * @return mark message.
     */
    @Override
    public String getMarkMessage(Task task) {
        return "Good job on completing your task!\n   " + task.toString();
    }

    /**
     * Returns the message to be printed when a command of type 'unmark' is executed.
     *
     * @param task the task that is unmarked.
     * @return unmark message.
     */
    @Override
    public String getUnmarkMessage(Task task) {
        return "Done, remember to do your task.\n   " + task.toString();
    }

    /**
     * Returns the message to be printed when a command of type 'exit' is executed.
     *
     * @return exit message.
     */
    @Override
    public String getExitMessage() {
        return "Goodbye! Thanks for using JJBA Bot!";
    }

    /**
     * Returns the message to be printed when a command of type 'bot' is executed.
     *
     * @return bot message.
     */
    @Override
    public String getBotMessage() {
        return "What can I do for you?";
    }

    /**
     * Returns the message to be printed when a command of type 'find' is executed.
     *
     * @return find message.
     */
    @Override
    public String getFindListMessage(boolean isEmpty) {
        return isEmpty ? "There are no matching tasks." : "Here are the matching task in your list:";
    }

    /**
     * Returns the image file path of the JJBABot.
     *
     * @return the image file path.
     */
    @Override
    public String getImagePath() {
        return "/images/JJBABot.png";
    }

    private String getTaskLeft(TaskList taskList) {
        return String.format("You have %s task%s in your list.", (taskList.size() > 0)
                ? taskList.size() : "no", (taskList.size() <= 1) ? "" : "s");
    }
}
