package duke.command;

import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents an invalid command in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class InvalidCommand extends Command {

    /**
     * No execution is required for an invalid command.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     */
    public String execute(TaskList taskList, Storage storage) {
        return "";
    }

}
