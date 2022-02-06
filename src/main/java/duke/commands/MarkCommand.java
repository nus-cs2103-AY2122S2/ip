package duke.commands;

import java.io.IOException;

import duke.info.exception.InvalidInputException;
import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.utils.Text;



public class MarkCommand extends Command {

    private final String indexToMark;

    /**
     * Constructs a MarkCommand with the specified indexToMark
     * @param indexToMark - index to mark as complete
     */
    public MarkCommand(String indexToMark) {
        this.indexToMark = indexToMark;
    }

    /**
     * Marks the task in the specified calendar at indexToMark as complete. The
     * string indexToMark is converted into an Integer index and which is used
     * in the Calendar markAsDone method. Then, display the success message showing
     * that the command was executed successfully on the specified ui handler and save
     * the new state of the calendar to the file using the specified storage handler.
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     * @throws InvalidInputException - if the indexToMark is invalid
     */
    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            Integer index = Integer.parseInt(indexToMark);
            calendar.markAsDone(index);
            ui.showTaskComplete(calendar.taskStringAtIndex(index));
            storage.save(calendar);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(Text.TEXT_INVALID_LIST_INDEX,
                    calendar.numOfEntries(), Integer.parseInt(indexToMark)));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(Text.TEXT_NON_INTEGER_LIST_INDEX, this.indexToMark));
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
