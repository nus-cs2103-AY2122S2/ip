package duke.command;

import duke.util.Save;
import duke.util.TaskList;

public class InvalidCommand extends Command {
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(TaskList tasks, Save save) {
        return this.message;
    }
}
