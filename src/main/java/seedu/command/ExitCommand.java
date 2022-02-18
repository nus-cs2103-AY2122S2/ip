package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    public String run(TaskList taskList, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
