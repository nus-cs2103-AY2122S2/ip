package seedu.commands;

import seedu.duke.DukeException;
import seedu.storage.TaskList;

public class ByeCommand extends Command {

    @Override
    public void validate(String input) throws DukeException {
        if (!input.equals("")) {
            throw new DukeException("Your 'bye' command is a bit too long.");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        // Task list is not in use in this scenario
        isExit = true;
        return "Good Bye!";
    }
}
