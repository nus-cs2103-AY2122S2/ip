package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public String run(TaskList tasksList, Storage storage) {
        assert tasksList != null : "ExitCommand->run: Tasks list cannot be null.";
        assert storage != null : "ExitCommand->run: Storage cannot be null.";

        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return "Bye. See you later!";
    }
}
