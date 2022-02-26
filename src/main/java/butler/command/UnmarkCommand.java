package butler.command;

import butler.ButlerInputException;
import butler.TaskList;
import butler.util.Storage;
import butler.util.ui.Ui;

public class UnmarkCommand extends Command {

    /** Index of taskList that is to be unmarked */
    private int index;

    /**
     * Constructs a UnmarkCommand object that executes a specified command.
     *
     * @param index Index of taskList that is to be marked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes command behavior.
     * In this case it unmarks a task at a given index from the taskList and stores the update list in a file.
     * Then it prints a success message to output, else it prints a failure message.
     *
     * @param taskList A managed list of tasks.
     * @param storage A handler class that handles file I/O, storing taskList details in a file.
     * @param ui A handler class that handles user input and output.
     * @throws ButlerInputException Throws the ButlerInputException after handling the NumberFormatException.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws ButlerInputException {
        try {
            String message = taskList.markAsUndone(index);
            storage.writeTaskListToFile(taskList);
            ui.setMessage(message);
        } catch (NumberFormatException exception) {
            throw new ButlerInputException("Error. Sorry Master, "
                    + "but a mark or unmark command must be followed by one Integer.\n");
        }
    }
}
