package duke.command;

import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

/**
 * The type Exit command.
 */
public class ExitCommand extends Command{

    /**
     * Instantiates a new Exit command.
     */
    public ExitCommand(){

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
        ui.closeReading();
    }
}
