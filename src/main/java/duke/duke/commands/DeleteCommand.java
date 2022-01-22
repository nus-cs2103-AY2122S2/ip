package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.ui.Ui;
import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.utils.Text;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final String indexToDelete;

    public DeleteCommand(String indexToUnmark) {
        this.indexToDelete = indexToUnmark;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            int index = Integer.parseInt(indexToDelete);
            String taskString = calendar.taskAtIndex(index);
            calendar.remove(index);
            ui.showTaskDeleted(taskString);
            storage.save(calendar);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(Text.TEXT_INVALID_LIST_INDEX, calendar.numOfEntries(), Integer.parseInt(indexToDelete)));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(Text.TEXT_NON_INTEGER_LIST_INDEX, this.indexToDelete));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
