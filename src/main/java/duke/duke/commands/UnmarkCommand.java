package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.ui.Ui;
import duke.info.task.Calendar;
import duke.storage.Storage;
import duke.utils.Text;

public class UnmarkCommand extends Command {

    private final String indexToUnmark;

    public UnmarkCommand(String indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            Integer index = Integer.parseInt(indexToUnmark);
            calendar.markAsNotDone(index);
            ui.showTaskIncomplete(calendar.taskAtIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(Text.TEXT_INVALID_LIST_INDEX, calendar.numOfEntries(), Integer.parseInt(indexToUnmark)));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(Text.TEXT_NON_INTEGER_LIST_INDEX, this.indexToUnmark));
        }
    }
}
