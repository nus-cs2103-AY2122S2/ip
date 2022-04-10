package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * Provides feedback if the input command is not recognised.
 */
public class UnknownCommand extends Command {
    private static final String MESSAGE = "I'm sorry, but I don't know what that means :-(";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return MESSAGE;
    }
}
