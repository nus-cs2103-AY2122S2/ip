package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ExitCommand extends Command {

    public ExitCommand() {
      super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }

}