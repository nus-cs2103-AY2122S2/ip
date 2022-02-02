package CommandSet;

import Helper.Storage;
import Helper.TaskList;

/**
 * <h1>ByeCommand</h1>
 * <p>
 * ByeCommand class is responsible for closing the program and storing the user tasks
 * for accessing in next run.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class ByeCommand extends Command {

    /**
     * Empty Constructor for ByeCommand.
     */
    ByeCommand() {
        super();
    }

    /**
     * stores the user tasks and username and closes the program.
     *
     * @param username the name of the user.
     * @param storage the storage instance associated with the current program.
     * @param taskList the list of user tasks.
     */
    public static void executeBye(String username, Storage storage, TaskList taskList) {

        String toStore = username + "\n" + taskList.getTasks();
        storage.storeTasks(toStore);
    }
}
