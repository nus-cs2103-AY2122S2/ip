package Commands;

import Tasks.TaskList;
import util.Storage;
import util.Ui;

public class ByeCommand extends DukeCommand{
    public ByeCommand(String description) {
        super(description);
    }

    /**
     * Executes when the user inputs the keyword "bye"
     * @param tasks The current list of tasks
     * @param ui The object that deals with user interaction
     * @param storage The object that deals with the management of the database
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
