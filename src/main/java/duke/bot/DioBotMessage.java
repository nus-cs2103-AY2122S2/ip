package duke.bot;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a collection of messages to respond to user input commands.
 * A <code>DioBotMessage</code> object can be created to respond to the user
 * in the DIO style.
 */
public class DioBotMessage extends BotMessage {
    /**
     * Returns the message to be printed when a command of type 'list' is executed.
     *
     * @param isEmpty whether list is empty.
     * @return list message.
     */
    @Override
    public String getListMessage(boolean isEmpty) {
        return isEmpty ? "I reject my humanity, Jojo!" : "Oh? You're Approaching Me?";
    }

    /**
     * Returns the message to be printed when an invalid command is entered.
     *
     * @return list message.
     */
    @Override
    public String getInvalidCommandMessage() {
        return "RODA ROLLA DA!";
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
        return "WRYYYYYYYYYYYY! \n   " + task + "\n" + getTaskLeft(taskList);
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
        return "*throws knifes\n   " + task;
    }

    /**
     * Returns the message to be printed when a command of type 'mark' is executed.
     *
     * @param task the task that is marked.
     * @return mark message.
     */
    @Override
    public String getMarkMessage(Task task) {
        return "KONO DIO DA!\n   " + task.toString();
    }

    /**
     * Returns the message to be printed when a command of type 'unmark' is executed.
     *
     * @param task the task that is unmarked.
     * @return unmark message.
     */
    @Override
    public String getUnmarkMessage(Task task) {
        return "Hinjaku! Hinjaku!\n   " + task.toString();
    }

    /**
     * Returns the message to be printed when a command of type 'exit' is executed.
     *
     * @return exit message.
     */
    @Override
    public String getExitMessage() {
        return "What?! I-Impossible... I-I am Dio... I am the mighty Dio!";
    }

    /**
     * Returns the message to be printed when a command of type 'bot' is executed.
     *
     * @return bot message.
     */
    @Override
    public String getBotMessage() {
        return "ZA WARUDO!";
    }

    /**
     * Returns the message to be printed when a command of type 'find' is executed.
     *
     * @return find message.
     */
    @Override
    public String getFindListMessage(boolean isEmpty) {
        return isEmpty ? "I reject my humanity, Jojo!" : "Oh? You're Approaching Me?";
    }

    /**
     * Returns the image file path of the DIOBot.
     *
     * @return the image file path.
     */
    @Override
    public String getImagePath() {
        return "/images/DIOBot.png";
    }

    private String getTaskLeft(TaskList taskList) {
        String muda = "";
        for (int i = 0; i < taskList.size(); i++) {
            muda += (i == taskList.size() - 1) ? "MUDA!" : "MUDA ";
        }

        if (muda.isEmpty()) {
            return "NANI!";
        } else {
            return muda;
        }
    }

}
