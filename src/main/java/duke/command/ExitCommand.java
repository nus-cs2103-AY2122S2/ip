package duke.command;


import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.close();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
