package istjbot.command;

import java.time.format.DateTimeParseException;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of finding the task(s) with user inputted date.
 */
public class DateCommand extends Command {
    /**
     * Constructor for this class.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information required for execution.
     */
    public DateCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return False as DateCommand is not a terminal command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of finding the task(s) with user inputted date.
     *
     * @param texts TextList responsible for searching of the task(s).
     * @param ui Text part of the User Interface.
     * @param storage Storage.
     * @throws BotException When the date user inputted is not a valid one.
     */
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        String dateString = extractDateString();
        try {
            String searchList = texts.searchByDateString(dateString);
            return ui.showTasksByDate(searchList);
        } catch (DateTimeParseException e) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }
    }

    private String extractDateString() throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");
        if (commandInfo.length != 2) {
            throw new BotException("As an IstjBot, I don't think that is a proper date you entered.");
        }
        return commandInfo[1];
    }
}
