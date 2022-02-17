package duke;

import duke.Command;

/**
 * Represents a command to exit Duke
 */
class ExitCommand extends Command {

    ExitCommand(String command) {
        super(command);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.byeString();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
