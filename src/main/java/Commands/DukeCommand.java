package Commands;

import Exceptions.DukeException;
import Tasks.Task;
import Tasks.TaskList;
import util.Storage;
import util.Ui;

import java.io.IOException;

public class DukeCommand {
    public String description;

    public DukeCommand(String description) {
        this.description = description;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException, IOException {

    }
}
