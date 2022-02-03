package duke.command;

import duke.task.TaskList;
import duke.utility.UI;
import duke.utility.Storage;

/**
 * Command for list out items in duke.task list
 */
public class ListCommand extends Command{

    public ListCommand(String command){
        super(command);
    }

    @Override
    public String execute(TaskList task, UI ui, Storage storage) {
        task.printTasks(ui);
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
