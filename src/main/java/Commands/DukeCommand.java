package Commands;

import Exceptions.DukeException;
import Tasks.Task;
import Tasks.TaskList;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class DukeCommand {
    public String commandBody;

    public DukeCommand(String description) {
        this.commandBody = description;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList task, Ui ui, Storage storage) throws DukeException, IOException {
        return "Empty";
    }
}
