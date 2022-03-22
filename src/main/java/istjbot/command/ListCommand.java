package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.text.TextList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of showing all the tasks or notes currently stored.
 */
public class ListCommand extends Command {
    /**
     * Constructor for this class.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information required for execution.
     */
    public ListCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return False as ListCommand is not a terminal command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of returning all the tasks or notes currently stored by the user.
     *
     * @param texts TextList responsible for returning all the tasks or notes.
     * @param ui Text part of the User Interface.
     * @param storage Storage.
     * @throws BotException When there are unnecessary terms attached other than list and type of text.
     */
    public String execute(TextList texts, Ui ui, Storage storage) throws BotException {
        boolean shouldListNotes = listNotes();
        String list;
        if (shouldListNotes) {
            list = texts.notesToString();
            return ui.showNotes(list);
        } else {
            list = texts.tasksToString();
            return ui.showTasks(list);
        }
    }

    private boolean listNotes() throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");

        if (commandInfo.length != 2) {
            throw new BotException("As an IstjBot, I can only accept 'list notes' or 'list tasks.'");
        }

        if (commandInfo[1].equals("notes")) {
            return true;
        } else if (commandInfo[1].equals("tasks")) {
            return false;
        }

        throw new BotException("As an IstjBot, I can only show list of tasks or list of notes.");
    }
}
