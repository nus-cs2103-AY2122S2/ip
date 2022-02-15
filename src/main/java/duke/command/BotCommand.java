package duke.command;

import duke.bot.BotType;
import duke.task.TaskList;

/**
 * Represents a command to change the bot of the program. A <code>BotCommand</code>
 * object records the bot type input by the user. When executing the object, it will
 * return a command feedback to change the bot to the specified bot type.
 */
public class BotCommand extends Command {
    public static final String COMMAND_WORD_JJBA = "jjba";
    public static final String COMMAND_WORD_DIO = "dio";

    private final BotType botType;

    /**
     * Creates an instance of a BotCommand object.
     *
     * @param botType the type of bot.
     */
    public BotCommand(BotType botType) {
        this.botType = botType;
    }

    /**
     * Returns a command feedback after the execution of the BotCommand.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.BOT.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.BOT);
    }

    /**
     * Returns the command word of the BotCommand based on the bot type
     * being specified in the command.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        switch (botType) {
        case JJBA:
            return COMMAND_WORD_JJBA;
        case DIO:
            return COMMAND_WORD_DIO;
        default:
            return "none";
        }
    }

    /**
     * Returns the bot type of the command specified in the command.
     *
     * @return bot type.
     */
    public BotType getBotType() {
        return botType;
    }
}
