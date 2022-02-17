package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    /**
     * Prints bye message in application
     * @param taskList list of tasks
     * @param ui ui of javafx
     * @return String exit message
     */
    public String execute(TaskList taskList, Ui ui) {
        Storage.saveData(taskList);
        return ui.printExitMessage();
    }

    /**
     * Terminates program and exist loop in main method
     *
     * @return true as bye command must exit
     */
    public boolean isExit() {
        return true;
    }
}
