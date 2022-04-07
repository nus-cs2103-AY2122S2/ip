package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Represents a command that list all tasks.
 */
public class ByeCommand extends Command {

    /**
     * Creates a ListCommand.
     *
     * @param description .
     */
    public ByeCommand(String description) {
        super(description);
    }

    /**
     * Execute ByeCommand to list out all tasks of a TaskList.
     *
     * @param taskList The TaskList for command act on.
     * @throws DukeException If taskList is empty.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) throws DukeException {
        Storage.saveToFile(taskList.getTaskStore());
        return "Bye. Hope to see you again soon!";
    }
}
