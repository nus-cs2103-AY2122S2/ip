package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;


public class FindCommand extends Command {

    private String search;

    @Override
    public void validate(String inst) throws DukeException {
        search = checkExist(inst);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.find(search);
    }
}
