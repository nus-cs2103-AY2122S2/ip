package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public abstract class StoreCommand extends Command {
    protected String[] tokens;
    public StoreCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList taskList, Ui io, Storage storage) throws DukeException {
        storage.save(taskList);
    }
}
