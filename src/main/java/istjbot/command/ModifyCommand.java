package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of marking and un-marking a task.
 */
public class ModifyCommand extends Command {

    public ModifyCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        int taskNumber = extractTaskNumber(texts);

        switch (this.getCommandEnum()) {
        case MARK:
            texts.markTask(taskNumber);
            storage.save(texts);
            return ui.showTaskMarked(texts.taskString(taskNumber));

        case UNMARK:
            texts.unmarkTask(taskNumber);
            storage.save(texts);
            return ui.showTaskUnmarked(texts.taskString(taskNumber));

        default:
            throw new BotException("As an IstjBot, this statement should not be shown to you.");
        }
    }

    private int extractTaskNumber(TextList texts) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length != 2) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandInfo[1]);
        } catch (NumberFormatException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }
        if (taskNumber < 1 || taskNumber > texts.taskListSize()) {
            throw new BotException("As an IstjBot, I don't think that is a proper index.");
        }
        return taskNumber;
    }
}
