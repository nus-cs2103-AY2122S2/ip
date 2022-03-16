package duke.command;

import duke.ui.Ui;
import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents the help command in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class HelpCommand extends Command {

    /**
     * Print all the commands of the Duke application and their corresponding usage.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     */
    public String execute(TaskList taskList, Storage storage) {
        return Ui.printHelp();
    }
}
