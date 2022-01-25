package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

public abstract class Command {
    private final CommandEnum commandEnum;
    private final String fullCommand;

    Command(CommandEnum commandEnum, String fullCommand) {
        this.commandEnum = commandEnum;
        this.fullCommand = fullCommand;
    }

    public abstract boolean isExit();
    public abstract void execute(TaskList task, Ui ui, Storage storage) throws BotException;

    public CommandEnum getCommandEnum() {
        return this.commandEnum;
    }

    public String getFullCommand() {
        return this.fullCommand;
    }
}
