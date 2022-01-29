package CommandSet;

/**
 * This file contains the implementation of ByeCommand class.
 * @author Saravanan Anuja Harish
 */

import Helper.Storage;
import Helper.TaskList;

public class ByeCommand extends Command {

    ByeCommand() {
        super();
    }

    public static void bye(String username, Storage storage, TaskList taskList) {

        String toStore = username + "\n" + taskList.getTasks();

        storage.storeTasks(toStore);
    }
}
