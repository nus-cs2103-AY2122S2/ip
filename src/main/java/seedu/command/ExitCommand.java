package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "ExitCommand->run: Tasks list cannot be null.";
        assert storage != null : "ExitCommand->run: Storage cannot be null.";

        return "Bye. See you later!";
    }
}
