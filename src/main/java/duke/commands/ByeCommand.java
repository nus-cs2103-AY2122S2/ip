package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class ByeCommand extends Command{

    public String execute(TaskList tasks, Ui ui) {
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
