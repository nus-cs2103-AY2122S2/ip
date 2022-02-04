package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class InvalidCommand extends Command {

    /**
     * Constructor for InvalidCommand
     *
     * @param userTaskString
     */
    public InvalidCommand(String userTaskString) {
        super(userTaskString);
    }

    /**
     * Throws exception when the command is invalid
     *
     * @param taskList
     * @param fileManager
     * @throws DukeException
     */
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.getUi().throwDukeException("Invalid Input! Please either add in a Todo, Deadline or Event!");
    }

}


