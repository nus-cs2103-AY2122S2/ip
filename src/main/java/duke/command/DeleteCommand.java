package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An instance of DeleteCommand.
 */
public class DeleteCommand extends Command {

    private final String entryNumber;

    /**
     * Instantiates a new Delete command.
     *
     * @param entryNumber the entry number that the user wants to remove/delete
     */
    public DeleteCommand(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    /**
     * Deletes/removes the specified task and prints a message upon its completion.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return string to indicate that the specified task has been removed from the list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                return "Your list is empty!";
            }

            int deletionIndex = Parser.parseInt(entryNumber) - 1;
            if (deletionIndex < 0 || deletionIndex >= tasks.size()) {
                return ui.showError("Invalid entry number!");
            } else {
                return ui.showAdditionOrDeletion(this, tasks.size(), tasks.removeTask(deletionIndex));
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
