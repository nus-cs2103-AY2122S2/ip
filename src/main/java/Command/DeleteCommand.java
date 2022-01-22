package Command;

import DukeUtils.*;
import Task.Task;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws CortanaException{
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
}
