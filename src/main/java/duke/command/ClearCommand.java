package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ClearCommand extends Command {

    /**
     * Clears the `TaskList` that is in memory.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWithDivider("Are you sure you want to clear your list? (Y/N)");

        boolean hasAnswered = false;
        while (!hasAnswered) {
            String userInput = ui.readCommand();
            switch (userInput.toUpperCase()) {
            case "Y":
                tasks.getTasks().clear();
                ui.printWithDivider("Your task list has been cleared.");
                hasAnswered = true;
                break;
            case "N":
                ui.printWithDivider("Your task list was NOT cleared.");
                hasAnswered = true;
                break;
            default:
                ui.printWithDivider("Please enter Y or N.");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
