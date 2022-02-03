package duke.command;

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
    public void execute(TaskList taskList, Storage storage) {
    }

    /**
     * Generate the usage guide for this command.
     *
     * @return Returns the formatted String value for printing for the usage guide.
     */
    public static String usage() {
        return """
                To close the application, use the bye command.
                  Usage: bye

                """;
    }

    /**
     * This method is used to check if this command closes the Duke Application.
     *
     * @return This return true as this command closes the Duke application.
     */
    @Override
    public boolean isExit() {
        return !super.isExit();
    }
}
