package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable)
            throws IOException;
    public abstract boolean isExit();
}
