package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

/**
 * Encapsulates the abstract concept of a Command.
 */
public abstract class Command {
    /** Specific CommandEnum that relates to the Command. */
    private final CommandEnum commandEnum;
    /** String of user's full command. */
    private final String fullCommand;

    /**
     * Constructor for Command.
     *
     * @param commandEnum Specific CommandEnum.
     * @param fullCommand String of user's full command.
     */
    Command(CommandEnum commandEnum, String fullCommand) {
        this.commandEnum = commandEnum;
        this.fullCommand = fullCommand;
    }

    public abstract boolean isExit();
    public abstract String execute(TaskList task, Ui ui, Storage storage) throws BotException;

    /**
     * Returns the specific CommandEnum related to the Command.
     *
     * @return CommandEnum that relates to the Command.
     */
    public CommandEnum getCommandEnum() {
        return this.commandEnum;
    }

    /**
     * Returns the full command the user has inputted, in the form of a String.
     *
     * @return String of user's full command.
     */
    public String getFullCommand() {
        return this.fullCommand;
    }
}
