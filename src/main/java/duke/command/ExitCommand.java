package duke.command;

import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Aww. Bye! See you again soon");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
