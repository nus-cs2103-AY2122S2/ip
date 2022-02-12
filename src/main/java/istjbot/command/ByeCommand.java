package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
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
     * @param texts TextList.
     * @param ui Text part of the User Interface.
     * @param storage Storage.
     * @throws BotException When the interaction cannot be finished due to extra information at the back.
     */
    @Override
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        checkForError();
        return ui.showBye();
    }

    private void checkForError() throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length > 1) {
            throw new BotException("As an IstjBot, I cannot understand more than bye.");
        }
    }
}
