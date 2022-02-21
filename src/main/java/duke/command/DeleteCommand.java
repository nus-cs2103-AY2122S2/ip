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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException(Messages.UNKNOWN_DELETE);
            }

            int taskNumber = Integer.parseInt(inputWords[1]);
            if (tasks.isOutOfBounds(taskNumber)) {
                throw new OutOfBoundsException(Messages.getOutOfBoundsMsg(taskNumber));
            }

            output = Ui.append(output, Messages.DELETE_SUCCESS);
            output = Ui.append(output, tasks.getTaskStatement(taskNumber - 1));

            tasks.remove(taskNumber - 1);
            storage.save(tasks);

            output = Ui.append(output, ui.printTaskCount(tasks));
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            output = ui.showError(e.getMessage());
        } catch (IOException e) {
            output = ui.showError(Messages.DELETE_ERROR);
        } catch (NumberFormatException e) {
            output = ui.showError(Messages.UNKNOWN_DELETE);
        }
        return output;
    }
}
