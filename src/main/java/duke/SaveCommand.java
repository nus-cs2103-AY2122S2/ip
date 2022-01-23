package duke;

import duke.Command;

import java.io.IOException;

/**
 * Command to save tasks into a specified location
 */
class SaveCommand extends Command {

    SaveCommand(String command) {
        super(command);
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.writeToFile(taskList.saveText());
            ui.showSave();
        } catch (IOException e) {
            ui.showSaveError(e);
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}
