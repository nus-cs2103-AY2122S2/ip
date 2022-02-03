package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

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
    public void execute(TaskList taskList, Ui ui,
            BotStorage botStorage, DateTable dateTable) {
        ui.endChat();
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
