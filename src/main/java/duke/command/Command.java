package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public interface Command {

    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws Exception;

    public abstract boolean isExit();
}
