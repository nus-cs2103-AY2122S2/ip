package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.main.EchoException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException;

}
