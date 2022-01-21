package duke.commands;

import duke.info.exception.InvalidInputException;
import duke.info.task.Calendar;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.utils.Text;

public class MarkCommand extends Command {

    private final String indexToMark;

    public MarkCommand(String indexToMark) {
        this.indexToMark = indexToMark;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) throws InvalidInputException {
        try {
            Integer index = Integer.parseInt(indexToMark);
            calendar.markAsDone(index);
            ui.showTaskComplete(calendar.taskAtIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException(String.format(Text.TEXT_INVALID_LIST_INDEX, calendar.numOfEntries(), Integer.parseInt(indexToMark)));
        } catch (NumberFormatException e) {
            throw new InvalidInputException(String.format(Text.TEXT_NON_INTEGER_LIST_INDEX, this.indexToMark));
        }
    }
}
