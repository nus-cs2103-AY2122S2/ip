package duke.command;

import duke.exception.InvalidArgumentException;
import duke.exception.OutOfBoundsException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private String[] inputWords;

    public UnmarkCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_UNMARK);
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(Messages.OUT_OF_BOUNDS_MSG(taskNumber));
            }
            tasks.get(taskNumber - 1).markAsUndone();
            ui.print(Messages.UNMARK_SUCCESS);
            ui.print(tasks.getTaskStatement(taskNumber - 1));
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Messages.DELETE_ERROR);
        } catch (NumberFormatException e) {
            ui.showError(Messages.UNKNOWN_UNMARK);
        }
    }
}
