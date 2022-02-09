package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }

}
