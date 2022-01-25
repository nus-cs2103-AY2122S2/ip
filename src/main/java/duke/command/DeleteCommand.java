package duke.command;

import duke.task.Task;

import duke.utils.CortanaException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.Objects;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException {
        try {
            Task taskDeleted = taskList.tasksArrayList.get(index);
            taskList.tasksArrayList.remove(index);
            taskList.taskSet.remove(taskDeleted);
            storage.writeFile(taskList);
            ui.deletedTask(taskList, taskDeleted);
        } catch (Exception e) {
            throw new CortanaException("No such task!");
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            DeleteCommand deleteCommand = (DeleteCommand) obj;
            return deleteCommand.index == this.index;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
