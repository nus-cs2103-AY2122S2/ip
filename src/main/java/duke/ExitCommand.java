package duke;

import duke.Command;

/**
 * Command to exit Duke
 */
class ExitCommand extends Command {

    ExitCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
