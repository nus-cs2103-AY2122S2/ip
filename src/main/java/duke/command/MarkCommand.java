package duke.command;

import java.io.IOException;

import duke.exception.InvalidArgumentException;
import duke.exception.OutOfBoundsException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private String[] inputWords;

    public MarkCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    /**
     * Marks the task as done.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!(inputWords.length == 2)) { //e.g mark 2, cannot be mark 2 2 or just mark
                throw new InvalidArgumentException(Messages.UNKNOWN_MARK);
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(Messages.getOutOfBoundsMsg(taskNumber));
            }
            tasks.get(taskNumber - 1).markAsDone();
            ui.print(Messages.MARK_SUCCESS);
            ui.print(tasks.getTaskStatement(taskNumber - 1));
            storage.save(tasks);
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Messages.DELETE_ERROR);
        } catch (NumberFormatException e) {
            ui.showError(Messages.UNKNOWN_MARK);
        }
    }
}
