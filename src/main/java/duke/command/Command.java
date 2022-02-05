package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public interface Command {

    public boolean execute(TaskList taskList, Ui ui, Storage storage);

}
