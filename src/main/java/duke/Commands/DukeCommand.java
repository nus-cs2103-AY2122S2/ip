package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

public abstract class DukeCommand {
    public String commandBody;

    public DukeCommand(String description) {
        this.commandBody = description;
    }

    public boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeException, IOException;
}
