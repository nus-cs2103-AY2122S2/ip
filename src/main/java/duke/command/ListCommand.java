package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{

    public ListCommand() {
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(ui.MSG_EMPTYTASK);
        } else {
            ui.print(ui.taskListMsg(taskList));
        }
    }

}
