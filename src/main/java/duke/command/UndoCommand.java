package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) throws DukeException {
        String output;
        TaskList latestTaskList = taskStack.pop();

        if (latestTaskList != null) {
            taskList.copy(latestTaskList);
            output = "LAST TASK UNDONE. CURRENT TASKS:" + taskList;
            storage.writeToFile(taskList);
        } else {
            output = "CAN'T UNDO FURTHER.";
        }

        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
