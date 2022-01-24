package duke.command;

import duke.task.Task;
import duke.function.Ui;
import duke.function.Storage;
import duke.function.TaskList;

public class HelpCommand extends Command {
    public HelpCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
