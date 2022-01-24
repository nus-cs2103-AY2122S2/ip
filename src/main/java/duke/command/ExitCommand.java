package duke.command;

import duke.function.TaskList;
import duke.function.Ui;
import duke.function.Storage;

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
