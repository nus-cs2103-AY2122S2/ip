package duke.command;

import java.io.IOException;

import duke.exception.InvalidArgumentException;
import duke.exception.OutOfBoundsException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private String[] inputWords;

    public DeleteCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    /**
     * Deletes a Task in TaskList.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_DELETE);
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(Messages.getOutOfBoundsMsg(taskNumber));
            }
            ui.print(Messages.DELETE_SUCCESS);
            ui.print(tasks.getTaskStatement(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            ui.printTaskCount(tasks);
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Messages.DELETE_ERROR);
        } catch (NumberFormatException e) {
            ui.showError(Messages.UNKNOWN_DELETE);
        }
    }
}
