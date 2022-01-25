package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, BotStorage botStorage, DateTable dateTable)
            throws IOException;
    public abstract boolean isExit();
}
