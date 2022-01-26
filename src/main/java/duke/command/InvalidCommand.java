package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class InvalidCommand extends Command{
    private Ui ui;

    public InvalidCommand(String userTaskString) {
        super(userTaskString);
        this.ui = new Ui();
    }

    /**
     * Throws exception when the command is invalid
     *
     * @param taskList
     * @param fileManager
     * @throws DukeException
     */
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        ui.throwDukeException("Invalid Input! Please either add in a Todo, Deadline or Event!");
    }

}


