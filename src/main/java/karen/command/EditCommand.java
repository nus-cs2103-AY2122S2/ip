package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;
import karen.task.Task;

/**
 * To edit attributes of Task objects
 */
public class EditCommand extends Command {
    private int taskIndex;
    private String editValue;

    /**
     * Constructor for EditCommand
     *
     * @param inputIndex 0-based index of Task to edit
     * @param inputValue Updated value to edit description
     */
    public EditCommand(int inputIndex, String inputValue) {
        taskIndex = inputIndex;
        editValue = inputValue;
    }

    /**
     * Edits Task's description at index taskIndex from Storage.
     * Saves the current state of taskList to local file directory.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return String result of output from successful execution of Command
     * @throws KarenException
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task item = storage.getTask(taskIndex);
            item.setDescription(editValue);
            storage.saveTasks();

            return ui.formatEditTask(item);
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(
                    String.format(InvalidMessage.INVALID_INDEX.toString(), taskIndex + 1));
        }
    }
}
