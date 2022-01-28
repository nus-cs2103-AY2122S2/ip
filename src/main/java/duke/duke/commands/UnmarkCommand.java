package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.ui.Ui;
import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.utils.Text;

import java.io.IOException;

public class UnmarkCommand extends Command {

    private final String indexToUnmark;

    /**
     * Constructs an UnmarkCommand with the specified indexToUnmark
     * @param indexToUnmark - index to mark as incomplete
     */
    public UnmarkCommand(String indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    /**
     * Marks the task in the specified calendar at indexToUnmark as incomplete. The string
     * indexToUnmark is converted into an Integer index which is used in the Calendar
     * markAsNotDone method. Then, display the success message showing that the command
     * was executed successfully on the specified ui handler and save the new state of the
     * calendar to the file using the specified storage handler.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     * @throws InvalidInputException - if the indexToUnmark is invalid
     */
    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            Integer index = Integer.parseInt(indexToUnmark);
            calendar.markAsNotDone(index);
            ui.showTaskIncomplete(calendar.taskStringAtIndex(index));
            storage.save(calendar);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(Text.TEXT_INVALID_LIST_INDEX, calendar.numOfEntries(), Integer.parseInt(indexToUnmark)));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(Text.TEXT_NON_INTEGER_LIST_INDEX, this.indexToUnmark));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
