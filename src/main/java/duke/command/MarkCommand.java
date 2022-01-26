package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task selectedTask = taskList.getTask(index);
        selectedTask.markAsComplete();
        ui.displayMarkedTask(selectedTask);
        storage.write(taskList.getTasks());
    }
}
