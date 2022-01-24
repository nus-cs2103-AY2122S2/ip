package duke.command;

import duke.bot.BotType;
import duke.exception.InvalidArgumentException;
import duke.task.TaskList;

public class BotCommand extends Command {
    public static final String COMMAND_WORD_JJBA = "jjba";
    public static final String COMMAND_WORD_DIO = "dio";

    private final BotType botType;

    public BotCommand(BotType botType) {
        this.botType = botType;
    }

    @Override
    public CommandFeedback execute(TaskList taskList) throws InvalidArgumentException {
        return new CommandFeedback(CommandType.BOT);
    }

    public BotType getBotType() {
        return botType;
    }
}
