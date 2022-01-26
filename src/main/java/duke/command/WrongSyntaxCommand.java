package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

/**
 * Activates for wrong syntax input
 */
public class WrongSyntaxCommand extends duke.command.Command {

    /**
     * Prints the wrong syntax messages in terminal
     *
     * @param taskList Reference of the <code>TaskList</code> object
     * @param ui Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable Reference of the <code>DateTable</code> object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable) {
        BotException exception = new BotException();
        exception.wrongSyntax();
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
