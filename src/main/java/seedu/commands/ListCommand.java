package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

public class ListCommand extends Command {

    @Override
    public void validate(String input) throws DukeException {
        if (!input.equals("")) {
            throw new DukeException("You have added unnecessary instructions in your list command.");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.toString();
    }
}
