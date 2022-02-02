package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    public ListCommand() {
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException(Ui.MSG_EMPTYTASK);
        } else {
            Ui.print(Ui.taskListMsg(taskList));
        }
    }

}
