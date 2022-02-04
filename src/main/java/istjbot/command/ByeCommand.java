package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of ending the interaction with IstjBot.
 */
public class ByeCommand extends Command {
    /**
     * Constructor for ByeCommand.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information (if any) for execution.
     */
    public ByeCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return True as ByeCommand is a terminal command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the procedure of ending the interaction with the IstjBot.
     *
     * @param tasks TaskList.
     * @param ui Ui responsible for printing out the terminal messages displayed to the user.
     * @param storage Storage.
     * @throws BotException When the interaction cannot be finished due to extra information at the back.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length > 1) {
            throw new BotException("As an IstjBot, I cannot understand more than bye.");
        }

        return ui.showBye();
    }
}
