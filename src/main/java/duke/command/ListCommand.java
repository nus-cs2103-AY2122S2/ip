package duke.command;

import duke.exception.DukeException;
import duke.manager.Ui;
import duke.manager.TaskList;
import duke.manager.Storage;

public class ListCommand extends Command{

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.print("Here are your tasks:");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            ui.print(i + 1 + "." + taskList.getTask(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
