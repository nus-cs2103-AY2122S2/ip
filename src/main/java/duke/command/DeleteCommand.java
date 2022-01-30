package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.logic.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.showMessage("TASK REMOVED:\n"
                + task + "\n"
                + taskList.size() + " TASK(S) NOW.");
        storage.writeToFile(taskList);
        return true;
    }
}
