package duke;

import java.io.IOException;

/**
 * Runs the command for user to show the list of tasks the user has on hand.
 */

public class ListCommand extends Command{

    /**
     * Executes the command and prints the list of tasks for the user.
     *
     * @param taskList The TaskList of the current user.
     * @param ui The user interface to show messages to users.
     * @param storage The file system for reading and writing into the database.
     */
    @Override
    String runCommand(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printList(taskList.convertListToString());
        storage.rewriteTask(taskList);
        return taskList.toString();
    }
}
