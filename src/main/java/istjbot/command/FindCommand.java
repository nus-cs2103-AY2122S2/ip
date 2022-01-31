package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");
        StringBuilder keyword = new StringBuilder();

        if (commandInfo.length == 1) {
            throw new BotException("As an IstjBot, I cannot find tasks with no keyword.");
        }

        for (int i = 1; i < commandInfo.length; i++) {
            if (i == commandInfo.length - 1) {
                keyword.append(commandInfo[i]);
            } else {
                keyword.append(commandInfo[i] + " ");
            }
        }

        String searchList = tasks.searchByKeywordString(keyword.toString());
        ui.showTasksByKeyword(searchList);
    }
}