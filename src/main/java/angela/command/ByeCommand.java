package angela.command;

import angela.datetime.DateTable;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * End chat command
 */
public class ByeCommand extends Command {

    /**
     * Prints out the end chat text line
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     */
    @Override
    public String execute(TaskList taskList, Ui ui,
            BotStorage botStorage, DateTable dateTable) {
        return ui.endChat();
    }

    /**
     * Checks if the command is the exit command
     *
     * @return True as this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
