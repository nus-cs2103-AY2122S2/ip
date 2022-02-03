package duke.command;

import duke.task.TaskList;
import duke.utility.UI;
import duke.utility.Storage;

/**
 * Command for Exit Duke app
 */
public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        ui.print("Aww. Bye! See you again soon");
        return null;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
