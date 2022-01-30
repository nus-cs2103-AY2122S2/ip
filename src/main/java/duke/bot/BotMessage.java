package duke.bot;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a collection of messages to respond to user input commands.
 * A <code>BotMessage</code> object cannot be created as it is an abstract base class
 * for all different types of bots.
 */
public abstract class BotMessage {
    /**
     * Returns the message to be printed when a command of type 'list' is executed.
     *
     * @param isEmpty whether list is empty.
     * @return list message.
     */
    public abstract String getListMessage(boolean isEmpty);

    /**
     * Returns the message to be printed when an invalid command is entered.
     *
     * @return list message.
     */
    public abstract String getInvalidCommandMessage();

    /**
     * Returns the message to be printed when a command of type 'add' is executed.
     *
     * @param taskList a task list containing all task.
     * @param task the task that is added.
     * @return add message.
     */
    public abstract String getAddMessage(TaskList taskList, Task task);

    /**
     * Returns the message to be printed when a command of type 'delete' is executed.
     *
     * @param taskList a task list containing all task.
     * @param task the task that is deleted.
     * @return delete message.
     */
    public abstract String getDeleteMessage(TaskList taskList, Task task);

    /**
     * Returns the message to be printed when a command of type 'mark' is executed.
     *
     * @param task the task that is marked.
     * @return mark message.
     */
    public abstract String getMarkMessage(Task task);

    /**
     * Returns the message to be printed when a command of type 'unmark' is executed.
     *
     * @param task the task that is unmarked.
     * @return unmark message.
     */
    public abstract String getUnmarkMessage(Task task);

    /**
     * Returns the message to be printed when a command of type 'exit' is executed.
     *
     * @return exit message.
     */
    public abstract String getExitMessage();

    /**
     * Returns the message to be printed when a command of type 'bot' is executed.
     *
     * @return bot message.
     */
    public abstract String getBotMessage();

    /**
     * Returns the message to be printed when a command of type 'find' is executed.
     *
     * @return find message.
     */
    public abstract String getFindListMessage(boolean isEmpty);

    /**
     * Returns the image file path of the Bot.
     *
     * @return the image file path.
     */
    public abstract String getImagePath();
}

