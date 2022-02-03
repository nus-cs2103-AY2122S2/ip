package seedu.commands;

import seedu.duke.DukeException;
import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;

public class FindCommand extends Command {

    private String search;

    @Override
    public void input(String inst) throws DukeException {
        search = checkExist(inst);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.find(search);
    }
}
