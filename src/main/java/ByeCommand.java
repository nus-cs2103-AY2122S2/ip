/**
 * This file contains the implementation of ByeCommand class.
 * @author Saravanan Anuja Harish
 */

public class ByeCommand extends Command {

    ByeCommand() {
        super();
    }

    static void bye(String username, Storage storage, TaskList taskList) {

        String toStore = username + "\n" + taskList.getTasks();

        storage.storeTasks(toStore);
    }
}
