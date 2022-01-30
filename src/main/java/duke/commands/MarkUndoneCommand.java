package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class MarkUndoneCommand extends MarkCommand{
    public MarkUndoneCommand(String userInput) {
        super(userInput);
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            if (taskManager.size() == 0) {
                ui.showMarkEmptyList();
                return false;
            } else {
                int index = Integer.parseInt(userInput.replaceFirst("unmark", "").strip()) - 1;

                if (index < 0 || index >= taskManager.size()) {
                    ui.showMarkOutOfBounds();
                    return false;
                } else {
                    boolean isSuccess = taskManager.markTaskUndone(index);
                    if (isSuccess) {
                        ui.showUnmarked(taskManager.getTask(index));
                        save(storage,ui,taskManager);
                    } else {
                        ui.showUnmarkNotNeeded(taskManager.getTask(index));
                    }
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number entered!");
        }
    }
}
