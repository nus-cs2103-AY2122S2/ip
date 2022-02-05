package angela.command;

import angela.datetime.DateTable;
import angela.exception.BotException;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Ui;

/**
 * Activates for wrong syntax input
 */
public class WrongSyntaxCommand extends angela.command.Command {

    /**
     * Prints the wrong syntax messages in terminal
     *
     * @param taskList   Reference of the <code>TaskList</code> object
     * @param ui         Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable  Reference of the <code>DateTable</code> object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) {
        BotException exception = new BotException();
        return exception.printWrongSyntaxError();
    }

    /**
     * Checks if the command is the exit command
     *
     * @return False as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
