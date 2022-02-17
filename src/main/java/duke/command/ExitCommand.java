package duke.command;

import duke.ui.Ui;
import duke.io.Storage;
import duke.task.TaskList;

/**
 * Represents a command to close the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class ExitCommand extends Command {

    /**
     * No execution is required for the exit command.
     *
     * @param taskList The list of task in the Duke application.
     * @param storage  Storage of task in local persistent disk.
     */
    public String execute(TaskList taskList, Storage storage) {
        return Ui.MSG_EXIT;
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return "To close the application, use the bye command. Then click the send button.\n"
                + "  Usage: bye\n\n";
    }

}
