package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;

import java.io.IOException;

public class WelcomeCommand extends Command {

    public WelcomeCommand() {
    }

    public String run(TaskList tasksList, Storage storage) {
        assert tasksList != null : "WelcomeCommand->run: Tasks list cannot be null.";
        assert storage != null : "WelcomeCommand->run: Storage cannot be null.";

        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return "Hello I'm Calcifer.\n What can I help you with today?\n";
    }
}
