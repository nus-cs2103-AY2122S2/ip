package duke;

import duke.Command;

import java.io.IOException;

/**
 * Represents a command to save tasks into a specified location
 */
class SaveCommand extends Command {

    SaveCommand(String command) {
        super(command);
    }

    @Override
    String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.writeToFile(taskList.saveText());
            return ui.saveString();
        } catch (IOException e) {
            return ui.saveErrorString(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
