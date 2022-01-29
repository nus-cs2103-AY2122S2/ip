package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    protected String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int deletionIndex = Parser.parseInt(index) - 1;
            if (deletionIndex < 0 || deletionIndex >= tasks.size()) {
                ui.showError("Invalid entry number!");
            } else {
                ui.showDeletion(tasks.size(), tasks.removeTask(deletionIndex));
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
