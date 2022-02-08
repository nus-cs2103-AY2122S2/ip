package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class UpdateNameCommand extends UpdateCommand {
    public UpdateNameCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            String s = userInput.replaceFirst("update", "").strip();
            String[] fields = s.split(" ");

            String newName = userInput.split("/name")[1];
            if (newName.strip().equals("")) {
                return ui.showNoName();
            }

            if (taskManager.size() == 0) {
                return ui.showMarkEmptyList();
            }

            int index = Integer.parseInt(fields[0]) - 1;

            if (index < 0 || index >= taskManager.size()) {
                return ui.showUpdateOutOfBounds();
            }

            Task toUpdate = taskManager.getTask(index);
            boolean isSuccess = toUpdate.updateName(newName);

            if (!isSuccess) {
                return ui.showSameNameError();
            }

            save(storage, ui, taskManager);
            return ui.showUpdateSuccess(toUpdate);

        } catch (NumberFormatException e) {
            return ui.showInvalidIntegerError();
        } catch (IndexOutOfBoundsException e) {
            return ui.showNoName();
        }
    }
}
