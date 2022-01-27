package command;

import task.TaskList;
import utility.UI;
import utility.Storage;

/**
 * Command for Exit Duke app
 */
public class ExitCommand extends Command{
    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.print("Aww. Bye! See you again soon");
    }


    @Override
    public boolean isExit() {
        return true;
    }
}
