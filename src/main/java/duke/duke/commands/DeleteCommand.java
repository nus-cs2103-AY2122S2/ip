package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.ui.Ui;
import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.utils.Text;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final String indexToDelete;

    /**
     * Constructs a DeleteCommand with the specified indexToDelete string
     * @param indexToDelete
     */
    public DeleteCommand(String indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Deletes the task at the specified indexToDelete. This is done by using the remove method
     * of the calendar object. Afterwards, display a message on the UI handler to indicate that
     * the task was successfully deleted, and use the save method in the storage handler to save
     * the new calendar to file.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     * @throws InvalidInputException - thrown when the index to delete is invalid
     */
    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            int index = Integer.parseInt(indexToDelete);
            String taskString = calendar.taskStringAtIndex(index);
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
