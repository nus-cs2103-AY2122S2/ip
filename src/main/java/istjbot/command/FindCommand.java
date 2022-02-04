package istjbot.command;

import istjbot.exception.BotException;
import istjbot.storage.Storage;
import istjbot.task.TaskList;
import istjbot.ui.Ui;

/**
 * Encapsulates the procedure of finding the task(s) with user inputted keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructor for this class.
     *
     * @param commandEnum CommandEnum indicating a specific type of command.
     * @param fullCommand Full information required for execution.
     */
    public FindCommand(CommandEnum commandEnum, String fullCommand) {
        super(commandEnum, fullCommand);
    }

    /**
     * Returns whether this command is a terminal command.
     *
     * @return False as FindCommand is not a terminal command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the procedure of finding the task(s) with user inputted keyword.
     *
     * @param tasks TaskList responsible for searching of the task(s).
     * @param ui Ui responsible for printing out the final messages displayed to the user.
     * @param storage Storage.
     * @throws BotException When the keyword is not specified by the user.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String[] commandInfo = this.getFullCommand().split(" ");
        StringBuilder keyword = new StringBuilder();

        // Exception handling
        if (commandInfo.length == 1) {
            throw new BotException("As an IstjBot, I cannot find tasks with no keyword.");
        }

        for (int i = 1; i < commandInfo.length; i++) {

            // For proper keyword spacing
            if (i == commandInfo.length - 1) {
                keyword.append(commandInfo[i]);
            } else {
                keyword.append(commandInfo[i] + " ");
            }
        }

        String searchList = tasks.searchByKeywordString(keyword.toString());
        return ui.showTasksByKeyword(searchList);
    }
}
