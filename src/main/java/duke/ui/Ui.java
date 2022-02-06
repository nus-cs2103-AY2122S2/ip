package duke.ui;

import duke.bot.BotMessage;
import duke.bot.BotType;
import duke.bot.DioBotMessage;
import duke.bot.JjbaBotMessage;
import duke.command.CommandFeedback;
import duke.task.TaskList;

/**
 * Represents the UI system of the program. A <code>Ui</code> object can be created to
 * print and format the output of the program.
 */
public class Ui {
    private BotMessage bot;

    /**
     * Creates a default instance of a Console object.
     */
    public Ui() {
        this(new JjbaBotMessage());
    }

    /**
     * Creates an instance of a Console object.
     *
     * @param bot type of message bot to output messages.
     */
    public Ui(BotMessage bot) {
        this.bot = bot;
    }

    /**
     * Returns a welcome message of the program.
     *
     * @return a welcome message.
     */
    public String getWelcomeMessage() {
        return "Welcome to JJBA Bot!\n" + bot.getBotMessage() + "\n";
    }

    /**
     * Prints the given task list.
     *
     * @param botMessage the botMessage being printed.
     * @param taskList a task list.
     */
    private String getTaskListMessage(String botMessage, TaskList taskList) {
        return botMessage + "\n" + taskList.toString();
    }

    /**
     * Prints an error message with a line separator
     * and a warning symbol '⚠'. The message will be
     * formatted with spacing.
     *
     * @param errorMsg error message to be formatted and print.
     */
    public String formatError(String errorMsg) {
        return "!!! " + errorMsg;
    }

    /**
     * Prints an error message due to an invalid command.
     *
     * @param errorMsg error message to be formatted and print.
     */
    public String getInvalidCommandMessage(String errorMsg) {
        if (errorMsg.isBlank()) {
            return formatError(bot.getInvalidCommandMessage());
        } else {
            return formatError(bot.getInvalidCommandMessage() + "\n  '" + errorMsg + "'");
        }
    }

    /**
     * Returns the output message of an executed command based on the command type
     * given in the command feedback.
     *
     * @param comFeed command feedback of an executed command.
     * @return the output message
     */
    public String getCommandFeedbackMessage(CommandFeedback comFeed) {
        switch (comFeed.cType) {
        case ADD:
            return bot.getAddMessage(comFeed.taskList, comFeed.task);
        case DELETE:
            return bot.getDeleteMessage(comFeed.taskList, comFeed.task);
        case LIST:
            return getTaskListMessage(bot.getListMessage(comFeed.taskList.isEmpty()), comFeed.taskList);
        case MARK:
            return bot.getMarkMessage(comFeed.task);
        case UNMARK:
            return bot.getUnmarkMessage(comFeed.task);
        case BOT:
            return bot.getBotMessage();
        case FIND:
            return getTaskListMessage(bot.getFindListMessage(comFeed.taskList.isEmpty()), comFeed.taskList);
        case EXIT:
            return bot.getExitMessage();
        default:
            return formatError("Unexpected error found in UI!");
        }
    }

    /**
     * Handles the changing of bot types for the program.
     *
     * @param botType bot type to be changed.
     */
    public void setBot(BotType botType) {
        switch (botType) {
        case JJBA:
            bot = new JjbaBotMessage();
            break;
        case DIO:
            bot = new DioBotMessage();
            break;
        default:
            break;
        }
    }

    public String getBotImagePath() {
        return bot.getImagePath();
    }
}
