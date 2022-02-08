package duke.commands;

import java.time.LocalDateTime;

import duke.Parser;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

public class UpdateDateCommand extends UpdateCommand {
    public UpdateDateCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            String s = userInput.replaceFirst("update", "").strip();
            String[] fields = s.split(" ");

            String newDate = userInput.split("/date")[1];
            if (newDate.strip().equals("")) {
                return ui.showNoDate();
            }
            LocalDateTime date = Parser.parseDateTime(newDate);

            if (taskManager.size() == 0) {
                return ui.showMarkEmptyList();
            }

            int index = Integer.parseInt(fields[0]) - 1;

            if (index < 0 || index >= taskManager.size()) {
                return ui.showUpdateOutOfBounds();
            }

            Task toUpdate = taskManager.getTask(index);

            if (toUpdate.getType() == 'T') {
                return ui.showIncompatibleType();
            }

            boolean isSuccess;
            if (date != null) {
                /* Recognizable date format entered */
                isSuccess = toUpdate.updateDate(date);
            } else {
                isSuccess = toUpdate.updateDate(newDate);
            }

            if (!isSuccess) {
                return ui.showSameDateError();
            }

            save(storage, ui, taskManager);
            return ui.showUpdateSuccess(toUpdate);

        } catch (NumberFormatException e) {
            return ui.showInvalidIntegerError();
        } catch (IndexOutOfBoundsException e) {
            return ui.showNoDate();
        }
    }
}
