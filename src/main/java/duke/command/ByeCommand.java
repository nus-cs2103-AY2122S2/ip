package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class ByeCommand extends Command {

    /**
     * Constructor for ByeCommand
     *
     * @param userTaskString
     */
    public ByeCommand(String userTaskString) {
        super(userTaskString);
    }

    /**
     * Executes saving of tasks and exiting.
     *
     * @param taskList
     * @param fileManager
     * @throws DukeException when not able to save tasks (File does not exist etc).
     */
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        fileManager.saveTasks();
        taskList.getUi().printBye();
        System.exit(0);
    }

}


