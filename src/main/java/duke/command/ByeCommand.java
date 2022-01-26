package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class ByeCommand extends Command{

    private int taskIdx;
    private Ui ui;
    private FileManager fileManager;

    public ByeCommand(String userTaskString) {
        super(userTaskString);
        this.ui = new Ui();
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
        ui.printBye();
        System.exit(0);
    }

}


