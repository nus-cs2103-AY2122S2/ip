package duke.command;

import duke.datetime.DateTable;
import duke.exception.BotException;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

public class WrongSyntaxCommand extends duke.command.Command {

    @Override
    public void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable) {
        BotException exception = new BotException();
        exception.wrongSyntax();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
