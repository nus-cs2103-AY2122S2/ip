package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int deletionIndex = Parser.parseInt(entryNumber) - 1;
            if (deletionIndex < 0 || deletionIndex >= tasks.size()) {
                ui.showError("Invalid entry number!");
            } else {
                Task deletedTask = tasks.removeTask(deletionIndex);
                ui.showDeletion(tasks.size(), deletedTask);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns `true` if the `Command` is `ExitCommand`.
     *
     * @return `true` if the `Command` is `ExitCommand`
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
